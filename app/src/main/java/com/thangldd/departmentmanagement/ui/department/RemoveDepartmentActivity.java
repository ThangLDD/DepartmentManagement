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

public class RemoveDepartmentActivity extends AppCompatActivity {

    private EditText editTextFindDepartment, editTextID, editTextName, editTextDescription;
    private Button buttonFindDepartment, buttonRemoveDepartment, buttonBackMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_department);
        matchLayout();
        listenUser();
    }

    private void listenUser() {
        buttonFindDepartment.setOnClickListener(view -> findDepartment());
        buttonRemoveDepartment.setOnClickListener(view -> removeDepartment());
        buttonBackMainActivity.setOnClickListener(view -> startMainActivity());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void removeDepartment() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String id = editTextID.getText().toString();
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        databaseHelper.removeDepartment(new Department(id, name, description));
        editTextID.setText("");
        editTextName.setText("");
        editTextDescription.setText("");
        Toast.makeText(this, "Xóa phòng ban thành công", Toast.LENGTH_SHORT).show();
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
        editTextFindDepartment = findViewById(R.id.edit_text_search_department_remove_department);
        editTextID = findViewById(R.id.edit_text_id_remove_department);
        editTextName = findViewById(R.id.edit_text_name_remove_department);
        editTextDescription = findViewById(R.id.edit_text_description_remove_department);
        buttonFindDepartment = findViewById(R.id.button_search_department_remove_department);
        buttonRemoveDepartment = findViewById(R.id.button_do_remove_department);
        buttonBackMainActivity = findViewById(R.id.button_back_remove_department);

        editTextID.setEnabled(false);
        editTextName.setEnabled(false);
        editTextDescription.setEnabled(false);
    }
}