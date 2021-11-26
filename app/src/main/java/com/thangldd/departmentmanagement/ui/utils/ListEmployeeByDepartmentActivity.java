package com.thangldd.departmentmanagement.ui.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thangldd.departmentmanagement.R;
import com.thangldd.departmentmanagement.data.database.DatabaseHelper;
import com.thangldd.departmentmanagement.data.model.Employee;
import com.thangldd.departmentmanagement.ui.MainActivity;

import java.util.ArrayList;

public class ListEmployeeByDepartmentActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;
    private Button button;
    private final ArrayList<String> listDepartment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee_by_department);
        getData();
        matchLayout();
        listenUser();
    }

    private void getData() {
        listDepartment.add("Tất cả");
        listDepartment.addAll(getDepartmentTitle());
    }

    private void listenUser() {
        spinner.setOnItemSelectedListener(new ItemSelectedListener());
        button.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void matchLayout() {
        spinner = findViewById(R.id.spinner_select_department_list_employee);
        listView = findViewById(R.id.listView_employee_department);
        button = findViewById(R.id.button_back_list_employee_department);

        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDepartment));
    }

    private ArrayList<Employee> getAllEmployees() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.getAllEmployeesWithDepartment();
    }

    private ArrayList<String> getDepartmentTitle() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.getAllDepartmentTitle();
    }

    private class ItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            getSelectedData(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(ListEmployeeByDepartmentActivity.this, "Hãy chọn phòng ban", Toast.LENGTH_SHORT).show();
        }
    }

    private void getSelectedData(int i) {
        ArrayAdapter<Employee> arrayAdapter;
        if (i == 0) {
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getAllEmployees());
        } else {
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getAllEmployeesByDepartment(listDepartment.get(i)));
        }
        listView.setAdapter(arrayAdapter);
    }

    private ArrayList<Employee> getAllEmployeesByDepartment(String departmentName) {
        DatabaseHelper databaseHelper = new DatabaseHelper(ListEmployeeByDepartmentActivity.this);
        return databaseHelper.getEmployeesByDepartment(departmentName);
    }
}