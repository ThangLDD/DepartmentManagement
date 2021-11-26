package com.thangldd.departmentmanagement.ui.department;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thangldd.departmentmanagement.R;
import com.thangldd.departmentmanagement.data.database.DatabaseHelper;
import com.thangldd.departmentmanagement.data.model.Department;
import com.thangldd.departmentmanagement.ui.MainActivity;

public class AddDepartmentActivity extends AppCompatActivity {

    private EditText editTextID, editTextName, editTextDescription;
    private Button buttonAddDepartment, buttonBackMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);
        matchLayout();
        listenUser();
    }

    private void listenUser() {
        buttonAddDepartment.setOnClickListener(view -> addDepartment());
        buttonBackMainActivity.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void addDepartment() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String id = editTextID.getText().toString();
        if (!databaseHelper.isDepartmentExist(id)) {
            String name = editTextName.getText().toString();
            String description = editTextDescription.getText().toString();
            databaseHelper.addDepartment(new Department(id, name, description));
            Toast.makeText(this, "Thêm phòng ban thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Phòng ban đã tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    private void matchLayout() {
        editTextID = findViewById(R.id.edit_text_id_add_department);
        editTextName = findViewById(R.id.edit_text_name_add_department);
        editTextDescription = findViewById(R.id.edit_text_description_add_department);
        buttonAddDepartment = findViewById(R.id.button_do_add_department);
        buttonBackMainActivity = findViewById(R.id.button_back_add_department);
    }
}