package com.example.demoapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private EditText editTextItem, editTextDescription;
    private Button addButton;
    private SearchView searchView;
    private ExpandableListView expandableListView;

    private CustomExpandableListAdapter adapter;
    private List<String> itemTitles; // List of item titles
    private HashMap<String, String> itemDescriptions; // Map of item descriptions

    private List<String> filteredTitles; // Filtered list for search
    private HashMap<String, String> filteredDescriptions; // Filtered map for search

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // Initialize views
        editTextItem = findViewById(R.id.editTextItem);
        editTextDescription = findViewById(R.id.editTextDescription);
        addButton = findViewById(R.id.addButton);
        searchView = findViewById(R.id.searchView);
        expandableListView = findViewById(R.id.expandableListView);

        // Initialize data
        itemTitles = new ArrayList<>();
        itemDescriptions = new HashMap<>();
        filteredTitles = new ArrayList<>();
        filteredDescriptions = new HashMap<>();

        // Set up the adapter
        adapter = new CustomExpandableListAdapter(this, filteredTitles, filteredDescriptions);
        expandableListView.setAdapter(adapter);

        // Populate filtered data with initial values
        updateFilteredData();

        // Add Button Click Listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editTextItem.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();

                if (TextUtils.isEmpty(item) || TextUtils.isEmpty(description)) {
                    Toast.makeText(ListViewActivity.this, "Please enter both item and description", Toast.LENGTH_SHORT).show();
                } else {
                    addItem(item, description);
                }
            }
        });

        // SearchView Query Listener for Filtering
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });
    }

    // Method to add a new item
    private void addItem(String item, String description) {
        if (itemTitles.contains(item)) {
            Toast.makeText(this, "Item already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add new item and description
        itemTitles.add(item);
        itemDescriptions.put(item, description);

        // Update filtered data and refresh the adapter
        updateFilteredData();
        adapter.notifyDataSetChanged();

        // Clear input fields
        editTextItem.setText("");
        editTextDescription.setText("");

        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
    }

    // Method to filter data based on the search query
    private void filterData(String query) {
        filteredTitles.clear();
        filteredDescriptions.clear();

        if (TextUtils.isEmpty(query)) {
            updateFilteredData(); // Show all items if query is empty
        } else {
            for (String title : itemTitles) {
                if (title.toLowerCase().contains(query.toLowerCase())) {
                    filteredTitles.add(title);
                    filteredDescriptions.put(title, itemDescriptions.get(title));
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    // Method to update the filtered data with all items (default)
    private void updateFilteredData() {
        filteredTitles.clear();
        filteredDescriptions.clear();

        filteredTitles.addAll(itemTitles);
        filteredDescriptions.putAll(itemDescriptions);
    }
}
