package com.thangldd.departmentmanagement.ui.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thangldd.departmentmanagement.R;
import com.thangldd.departmentmanagement.data.database.DatabaseHelper;
import com.thangldd.departmentmanagement.data.model.Employee;
import com.thangldd.departmentmanagement.ui.MainActivity;

import java.util.ArrayList;

public class AssignEmployeeActivity extends AppCompatActivity {

    private EditText editTextFindEmployee, editTextID, editTextName, editTextDateOB, editTextHometown, editTextDepartment;
    private Button buttonFindEmployee, buttonAssignEmployee, buttonBackMainActivity;
    private Spinner spinner;
    private final ArrayList<String> listDepartment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_employee);
        getData();
        initialView();
        listenUser();
    }

    private void getData() {
        listDepartment.add("Chọn phòng ban");
        listDepartment.addAll(getDepartmentTitle());
    }

    private void listenUser() {
        buttonFindEmployee.setOnClickListener(view -> findEmployee());
        buttonAssignEmployee.setOnClickListener(view -> assignEmployee());
        buttonBackMainActivity.setOnClickListener(view -> startMainActivity());
        spinner.setOnItemSelectedListener(new ItemSelectedListener());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void assignEmployee() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String id = editTextID.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String dateOB = editTextDateOB.getText().toString().trim();
        String hometown = editTextHometown.getText().toString().trim();
        String department = editTextDepartment.getText().toString().trim();
        databaseHelper.editEmployeeWithDepartment(new Employee(id, name, dateOB, hometown, department));
        Toast.makeText(this, "Gán nhân viên thành công", Toast.LENGTH_SHORT).show();
    }

    private void findEmployee() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String idKey = editTextFindEmployee.getText().toString();
        if (databaseHelper.isEmployeeExist(idKey)) {
            Employee employee = databaseHelper.getEmployeeByID(idKey);
            editTextID.setText(employee.getId().trim());
            editTextName.setText(employee.getName().trim());
            editTextDateOB.setText(employee.getDateOB().trim());
            editTextHometown.setText(employee.getHometown().trim());
            if (employee.getDepartment() == null) {
                editTextDepartment.setText("");
            } else {
                editTextDepartment.setText(employee.getDepartment().trim());
            }
        } else {
            Toast.makeText(this, "Nhân viên không tồn tại", Toast.LENGTH_SHORT).show();
            editTextID.setText("");
            editTextName.setText("");
            editTextDateOB.setText("");
            editTextHometown.setText("");
            editTextDepartment.setText("");
        }
    }

    private void initialView() {
        editTextFindEmployee = findViewById(R.id.edit_text_search_employee_assign_employee);
        editTextID = findViewById(R.id.edit_text_id_assign_employee);
        editTextName = findViewById(R.id.edit_text_name_assign_employee);
        editTextDateOB = findViewById(R.id.edit_text_date_ob_assign_employee);
        editTextHometown = findViewById(R.id.edit_text_hometown_assign_employee);
        editTextDepartment = findViewById(R.id.edit_text_department_assign_employee);
        buttonFindEmployee = findViewById(R.id.button_search_employee_assign_employee);
        buttonAssignEmployee = findViewById(R.id.button_do_assign_employee);
        buttonBackMainActivity = findViewById(R.id.button_back_assign_employee);
        spinner = findViewById(R.id.spinner_select_department_assign_employee);

        editTextID.setEnabled(false);
        editTextName.setEnabled(false);
        editTextDateOB.setEnabled(false);
        editTextHometown.setEnabled(false);
        editTextDepartment.setEnabled(false);

        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDepartment));
    }

    private ArrayList<String> getDepartmentTitle() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.getAllDepartmentTitle();
    }

    private class ItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 0) {
                editTextDepartment.setText("");
            } else {
                editTextDepartment.setText(listDepartment.get(i));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(AssignEmployeeActivity.this, "Hãy chọn phòng ban", Toast.LENGTH_SHORT).show();
        }
    }
}