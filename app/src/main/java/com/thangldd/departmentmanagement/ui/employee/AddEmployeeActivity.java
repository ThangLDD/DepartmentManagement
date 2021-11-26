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

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText editTextID, editTextName, editTextDateOB, editTextHometown;
    private Button buttonDatePicker, buttonAddEmployee, buttonStartMainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        initializeView();
        listenUser();
    }

    private void listenUser() {
        buttonDatePicker.setOnClickListener(view -> pickDate());
        buttonAddEmployee.setOnClickListener(view -> addEmployee());
        buttonStartMainActivity.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void addEmployee() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        if (!databaseHelper.isEmployeeExist((editTextID.getText().toString()))) {
            String id = editTextID.getText().toString().trim();
            String name = editTextName.getText().toString().trim();
            String dateOB = editTextDateOB.getText().toString().trim();
            String hometown = editTextHometown.getText().toString().trim();

            Employee employee = new Employee(id, name, dateOB, hometown);

            databaseHelper.addEmployee(employee);

            Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nhân viên đã tồn tại", Toast.LENGTH_SHORT).show();
        }
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

    private void initializeView() {
        editTextID = findViewById(R.id.edit_text_id_add_employee);
        editTextName = findViewById(R.id.edit_text_name_add_employee);
        editTextDateOB = findViewById(R.id.edit_text_date_ob_add_employee);
        editTextHometown = findViewById(R.id.edit_text_hometown_add_employee);
        buttonDatePicker = findViewById(R.id.button_date_picker_add_employee);
        buttonAddEmployee = findViewById(R.id.button_do_add_employee);
        buttonStartMainActivity = findViewById(R.id.button_back_add_employee);
        editTextDateOB.setEnabled(false);
    }
}