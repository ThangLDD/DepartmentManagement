package com.thangldd.departmentmanagement.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thangldd.departmentmanagement.R;
import com.thangldd.departmentmanagement.ui.department.AddDepartmentActivity;
import com.thangldd.departmentmanagement.ui.utils.AssignEmployeeActivity;
import com.thangldd.departmentmanagement.ui.department.EditDepartmentActivity;
import com.thangldd.departmentmanagement.ui.department.RemoveDepartmentActivity;
import com.thangldd.departmentmanagement.ui.employee.AddEmployeeActivity;
import com.thangldd.departmentmanagement.ui.employee.EditEmployeeActivity;
import com.thangldd.departmentmanagement.ui.employee.RemoveEmployeeActivity;
import com.thangldd.departmentmanagement.ui.utils.ListEmployeeByDepartmentActivity;
import com.thangldd.departmentmanagement.ui.utils.ListEmployeeByHometownActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAddEmployee, buttonEditEmployee, buttonRemoveEmployee,
            buttonAddDepartment, buttonEditDepartment, buttonRemoveDepartment,
            buttonListEmployeeByDepartment, buttonListEmployeeByHometown, buttonAssignEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        listenUser();
    }

    private void listenUser() {
        buttonAddEmployee.setOnClickListener(this);
        buttonEditEmployee.setOnClickListener(this);
        buttonRemoveEmployee.setOnClickListener(this);
        buttonAddDepartment.setOnClickListener(this);
        buttonEditDepartment.setOnClickListener(this);
        buttonRemoveDepartment.setOnClickListener(this);
        buttonListEmployeeByDepartment.setOnClickListener(this);
        buttonListEmployeeByHometown.setOnClickListener(this);
        buttonAssignEmployee.setOnClickListener(this);
    }

    private void initializeView() {
        buttonAddEmployee = findViewById(R.id.button_add_employee_menu);
        buttonEditEmployee = findViewById(R.id.button_edit_employee_menu);
        buttonRemoveEmployee = findViewById(R.id.button_remove_employee_menu);
        buttonAddDepartment = findViewById(R.id.button_add_department_menu);
        buttonEditDepartment = findViewById(R.id.button_edit_department_menu);
        buttonRemoveDepartment = findViewById(R.id.button_remove_department_menu);
        buttonListEmployeeByDepartment = findViewById(R.id.button_list_employee_department_menu);
        buttonListEmployeeByHometown = findViewById(R.id.button_list_employee_hometown_menu);
        buttonAssignEmployee = findViewById(R.id.button_assign_employee);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button_add_employee_menu:
                intent = new Intent(this, AddEmployeeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_edit_employee_menu:
                intent = new Intent(this, EditEmployeeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_remove_employee_menu:
                intent = new Intent(this, RemoveEmployeeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_add_department_menu:
                intent = new Intent(this, AddDepartmentActivity.class);
                startActivity(intent);
                break;
            case R.id.button_edit_department_menu:
                intent = new Intent(this, EditDepartmentActivity.class);
                startActivity(intent);
                break;
            case R.id.button_remove_department_menu:
                intent = new Intent(this, RemoveDepartmentActivity.class);
                startActivity(intent);
                break;
            case R.id.button_list_employee_department_menu:
                intent = new Intent(this, ListEmployeeByDepartmentActivity.class);
                startActivity(intent);
                break;
            case R.id.button_list_employee_hometown_menu:
                intent = new Intent(this, ListEmployeeByHometownActivity.class);
                startActivity(intent);
                break;
            case R.id.button_assign_employee:
                intent = new Intent(this, AssignEmployeeActivity.class);
                startActivity(intent);
                break;
        }
    }
}