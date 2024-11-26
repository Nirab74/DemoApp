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
    private List<String> itemTitles;
    private HashMap<String, String> itemDescriptions;

    private List<String> filteredTitles;
    private HashMap<String, String> filteredDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        editTextItem = findViewById(R.id.editTextItem);
        editTextDescription = findViewById(R.id.editTextDescription);
        addButton = findViewById(R.id.addButton);
        searchView = findViewById(R.id.searchView);
        expandableListView = findViewById(R.id.expandableListView);


        itemTitles = new ArrayList<>();
        itemDescriptions = new HashMap<>();
        filteredTitles = new ArrayList<>();
        filteredDescriptions = new HashMap<>();


        adapter = new CustomExpandableListAdapter(this, filteredTitles, filteredDescriptions);
        expandableListView.setAdapter(adapter);


        updateFilteredData();


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


    private void addItem(String item, String description) {
        if (itemTitles.contains(item)) {
            Toast.makeText(this, "Item already exists", Toast.LENGTH_SHORT).show();
            return;
        }


        itemTitles.add(item);
        itemDescriptions.put(item, description);


        updateFilteredData();
        adapter.notifyDataSetChanged();


        editTextItem.setText("");
        editTextDescription.setText("");

        Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
    }


    private void filterData(String query) {
        filteredTitles.clear();
        filteredDescriptions.clear();

        if (TextUtils.isEmpty(query)) {
            updateFilteredData();
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


    private void updateFilteredData() {
        filteredTitles.clear();
        filteredDescriptions.clear();

        filteredTitles.addAll(itemTitles);
        filteredDescriptions.putAll(itemDescriptions);
    }
}
