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

public class EditDepartmentActivity extends AppCompatActivity {

    private EditText editTextFindDepartment, editTextID, editTextName, editTextDescription;
    private Button buttonFindDepartment, buttonEditDepartment, buttonBackMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_department);
        matchLayout();
        listenUser();
    }

    private void listenUser() {
        buttonFindDepartment.setOnClickListener(view -> findDepartment());
        buttonEditDepartment.setOnClickListener(view -> editDepartment());
        buttonBackMainActivity.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void editDepartment() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String id = editTextID.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        databaseHelper.editDepartment(new Department(id, name, description));
        Toast.makeText(this, "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
    }

    private void findDepartment() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String id = editTextFindDepartment.getText().toString();
        if (databaseHelper.isDepartmentExist(id)) {
            Department department = databaseHelper.getDepartmentByID(id);
            editTextID.setText(department.getId().trim());
            editTextName.setText(department.getName().trim());
            editTextDescription.setText(department.getDescription().trim());
        } else {
            Toast.makeText(this, "Phòng ban không tồn tại", Toast.LENGTH_SHORT).show();
            editTextID.setText("");
            editTextName.setText("");
            editTextDescription.setText("");
        }
    }

    private void matchLayout() {
        editTextFindDepartment = findViewById(R.id.edit_text_search_department_edit_department);
        editTextID = findViewById(R.id.edit_text_id_edit_department);
        editTextName = findViewById(R.id.edit_text_name_edit_department);
        editTextDescription = findViewById(R.id.edit_text_description_edit_department);
        buttonFindDepartment = findViewById(R.id.button_search_department_edit_department);
        buttonEditDepartment = findViewById(R.id.button_do_edit_department);
        buttonBackMainActivity = findViewById(R.id.button_back_edit_department);
        editTextID.setEnabled(false);
    }
}