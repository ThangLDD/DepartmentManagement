package com.thangldd.departmentmanagement.ui.employee;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

public class EditEmployeeActivity extends AppCompatActivity {

    private EditText editTextFindEmployee, editTextID, editTextName, editTextDateOB, editTextHomeTown;
    private Button buttonFindEmployee, buttonPickDate, buttonEditEmployee, buttonBackMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);
        matchLayout();
        listenUser();
    }

    private void listenUser() {
        buttonFindEmployee.setOnClickListener(view -> findEmployee());
        buttonPickDate.setOnClickListener(view -> pickDate());
        buttonEditEmployee.setOnClickListener(view -> editEmployee());
        buttonBackMain.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void editEmployee() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String id = editTextID.getText().toString();
        String name = editTextName.getText().toString();
        String dateOB = editTextDateOB.getText().toString();
        String hometown = editTextHomeTown.getText().toString();
        databaseHelper.editEmployee(new Employee(id, name, dateOB, hometown));
        Toast.makeText(this, "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
    }

    private void pickDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, i, i1, i2) -> {
            String text = i2 + "/" + (i1 + 1) + "/" + i;

            editTextDateOB.setText(text);
        };

        final Calendar c = Calendar.getInstance();
        int lastSelectedYear = c.get(Calendar.YEAR);
        int lastSelectedMonth = c.get(Calendar.MONTH);
        int lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener,
                lastSelectedYear,
                lastSelectedMonth,
                lastSelectedDayOfMonth);
        datePickerDialog.show();
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
            editTextID.setText("");
            editTextName.setText("");
            editTextDateOB.setText("");
            editTextHomeTown.setText("");
        }
    }

    private void matchLayout() {
        editTextFindEmployee = findViewById(R.id.edit_text_search_employee_edit_employee);
        editTextID = findViewById(R.id.edit_text_id_edit_employee);
        editTextName = findViewById(R.id.edit_text_name_edit_employee);
        editTextDateOB = findViewById(R.id.edit_text_date_ob_edit_employee);
        editTextHomeTown = findViewById(R.id.edit_text_hometown_edit_employee);
        buttonFindEmployee = findViewById(R.id.button_search_employee_edit_employee);
        buttonPickDate = findViewById(R.id.button_date_picker_edit_employee);
        buttonEditEmployee = findViewById(R.id.button_edit_edit_employee);
        buttonBackMain = findViewById(R.id.button_back_edit_employee);
        editTextID.setEnabled(false);
    }
}