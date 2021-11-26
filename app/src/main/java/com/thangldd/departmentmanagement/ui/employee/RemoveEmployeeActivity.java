package com.thangldd.departmentmanagement.ui.employee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thangldd.departmentmanagement.R;
import com.thangldd.departmentmanagement.data.database.DatabaseHelper;
import com.thangldd.departmentmanagement.data.model.Employee;
import com.thangldd.departmentmanagement.ui.MainActivity;

public class RemoveEmployeeActivity extends AppCompatActivity {

    private EditText editTextFindEmployee, editTextID, editTextName, editTextDateOB, editTextHomeTown;
    private Button buttonFind, buttonRemoveEmployee, buttonBackMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_employee);
        matchLayout();
        listenUser();
    }

    private void listenUser() {
        buttonFind.setOnClickListener(view -> findEmployee());
        buttonRemoveEmployee.setOnClickListener(view -> removeEmployee());
        buttonBackMainActivity.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void removeEmployee() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String id = editTextID.getText().toString();
        String name = editTextName.getText().toString();
        String dateOB = editTextDateOB.getText().toString();
        String hometown = editTextHomeTown.getText().toString();
        databaseHelper.removeEmployee(new Employee(id, name, dateOB, hometown));
        editTextID.setText("");
        editTextName.setText("");
        editTextDateOB.setText("");
        editTextHomeTown.setText("");
        Toast.makeText(this, "Xóa nhân viên thành công", Toast.LENGTH_LONG).show();
    }

    private void findEmployee() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String idKey = editTextFindEmployee.getText().toString();
        if (databaseHelper.isEmployeeExist(idKey)) {
            Employee employee = databaseHelper.getEmployeeByID(idKey);
            editTextID.setText(employee.getId().trim());
            editTextName.setText(employee.getName().trim());
            editTextDateOB.setText(employee.getDateOB().trim());
            editTextHomeTown.setText(employee.getHometown().trim());
        } else {
            Toast.makeText(this, "Nhân viên không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    private void matchLayout() {
        editTextFindEmployee = findViewById(R.id.edit_text_search_employee_remove_employee);
        editTextID = findViewById(R.id.edit_text_id_remove_employee);
        editTextName = findViewById(R.id.edit_text_name_remove_employee);
        editTextDateOB = findViewById(R.id.edit_text_date_ob_remove_employee);
        editTextHomeTown = findViewById(R.id.edit_text_hometown_remove_employee);
        buttonFind = findViewById(R.id.button_search_employee_remove_employee);
        buttonRemoveEmployee = findViewById(R.id.button_do_remove_employee);
        buttonBackMainActivity = findViewById(R.id.button_back_remove_employee);

        editTextID.setEnabled(false);
        editTextName.setEnabled(false);
        editTextDateOB.setEnabled(false);
        editTextHomeTown.setEnabled(false);
    }
}