package com.thangldd.departmentmanagement.ui.utils;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.thangldd.departmentmanagement.R;
import com.thangldd.departmentmanagement.data.database.DatabaseHelper;
import com.thangldd.departmentmanagement.data.model.Employee;
import com.thangldd.departmentmanagement.ui.MainActivity;

import java.util.ArrayList;

public class ListEmployeeByHometownActivity extends AppCompatActivity {

    private Button buttonBackMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee_by_hometown);
        initView();
        listenUser();
    }

    private void listenUser() {
        buttonBackMainActivity.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initView() {
        ListView listView = findViewById(R.id.listView_employee_hometown);
        buttonBackMainActivity = findViewById(R.id.button_back_list_employee_hometown);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getData()));
    }

    private ArrayList<Employee> getData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.getEmployeesByHometown("Hải Phòng");
    }
}