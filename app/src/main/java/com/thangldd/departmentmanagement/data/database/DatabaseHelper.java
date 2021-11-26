package com.thangldd.departmentmanagement.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.thangldd.departmentmanagement.data.model.Department;
import com.thangldd.departmentmanagement.data.model.Employee;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DepartmentManagement.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EMPLOYEE = "Employee";
    private static final String TABLE_DEPARTMENT = "Department";

    private static final String EMPLOYEE_ID = "employee_id";
    private static final String EMPLOYEE_NAME = "employee_name";
    private static final String EMPLOYEE_DATE_OF_BIRTH = "employee_dateOB";
    private static final String EMPLOYEE_HOMETOWN = "employee_hometown";
    private static final String EMPLOYEE_DEPARTMENT = "employee_department";

    private static final String DEPARTMENT_ID = "department_id";
    private static final String DEPARTMENT_NAME = "department_name";
    private static final String DEPARTMENT_DESCRIPTION = "department_description";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(queryCreateTableEmployee());
        sqLiteDatabase.execSQL(queryCreateTableDepartment());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(queryDropTable(TABLE_EMPLOYEE));
        sqLiteDatabase.execSQL(queryDropTable(TABLE_DEPARTMENT));
        onCreate(sqLiteDatabase);
    }

    private String queryCreateTableEmployee() {
        return "CREATE TABLE " + DatabaseHelper.TABLE_EMPLOYEE + " (" +
                EMPLOYEE_ID + " VARCHAR (255) PRIMARY KEY, " +
                EMPLOYEE_NAME + " VARCHAR (255), " +
                EMPLOYEE_DATE_OF_BIRTH + " VARCHAR (255), " +
                EMPLOYEE_HOMETOWN + " VARCHAR (255), " +
                EMPLOYEE_DEPARTMENT + " VARCHAR (255)" +
                ")";
    }

    private String queryCreateTableDepartment() {
        return "CREATE TABLE " + DatabaseHelper.TABLE_DEPARTMENT + " (" +
                DEPARTMENT_ID + " VARCHAR (255) PRIMARY KEY, " +
                DEPARTMENT_NAME + " VARCHAR (255), " +
                DEPARTMENT_DESCRIPTION + " VARCHAR (255)" +
                ")";
    }

    private String queryDropTable(String name) {
        return "DROP TABLE IF EXISTS " + name;
    }

    public ArrayList<Employee> getAllEmployeesWithDepartment() {
        ArrayList<Employee> employees = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_EMPLOYEE;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String dateOB = cursor.getString(2);
            String hometown = cursor.getString(3);
            String department = cursor.getString(4);

            employees.add(new Employee(id, name, dateOB, hometown, department));
            cursor.moveToNext();
        }

        cursor.close();
        return employees;
    }

    public ArrayList<Employee> getAllEmployeesWithoutDepartment() {
        ArrayList<Employee> employees = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_EMPLOYEE;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String dateOB = cursor.getString(2);
            String hometown = cursor.getString(3);

            employees.add(new Employee(id, name, dateOB, hometown));
            cursor.moveToNext();
        }

        cursor.close();
        return employees;
    }

    public Employee getEmployeeByID(String idKey) {
        Employee employee = new Employee();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = new String[]{idKey};
        Cursor cursor = sqLiteDatabase.query(TABLE_EMPLOYEE, null, EMPLOYEE_ID + " = ?", selectionArgs, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String dateOB = cursor.getString(2);
                String hometown = cursor.getString(3);
                String department = cursor.getString(4);

                employee = new Employee(id, name, dateOB, hometown, department);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return employee;
    }

    public ArrayList<Employee> getEmployeesByDepartment(String departmentName) {
        ArrayList<Employee> employees = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = new String[]{departmentName};
        Cursor cursor = sqLiteDatabase.query(TABLE_EMPLOYEE, null, EMPLOYEE_DEPARTMENT + " = ?", selectionArgs, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String dateOB = cursor.getString(2);
                String hometown = cursor.getString(3);
                String department = cursor.getString(4);

                employees.add(new Employee(id, name, dateOB, hometown, department));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return employees;
    }

    public ArrayList<Employee> getEmployeesByHometown(String hometownKey) {
        ArrayList<Employee> employees = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = new String[]{hometownKey};
        Cursor cursor = sqLiteDatabase.query(TABLE_EMPLOYEE, null, EMPLOYEE_HOMETOWN + " = ?", selectionArgs, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String dateOB = cursor.getString(2);
                String hometown = cursor.getString(3);
                String department = cursor.getString(4);

                employees.add(new Employee(id, name, dateOB, hometown, department));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EMPLOYEE_ID, employee.getId());
        contentValues.put(EMPLOYEE_NAME, employee.getName());
        contentValues.put(EMPLOYEE_DATE_OF_BIRTH, employee.getDateOB());
        contentValues.put(EMPLOYEE_HOMETOWN, employee.getHometown());

        sqLiteDatabase.insert(TABLE_EMPLOYEE, null, contentValues);
        sqLiteDatabase.close();
    }

    public void editEmployee(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] selectionArgs = new String[]{employee.getId()};

        contentValues.put(EMPLOYEE_NAME, employee.getName());
        contentValues.put(EMPLOYEE_DATE_OF_BIRTH, employee.getDateOB());
        contentValues.put(EMPLOYEE_HOMETOWN, employee.getHometown());

        sqLiteDatabase.update(TABLE_EMPLOYEE, contentValues, EMPLOYEE_ID + " = ?", selectionArgs);
        sqLiteDatabase.close();
    }

    public void editEmployeeWithDepartment(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] selectionArgs = new String[]{employee.getId()};

        contentValues.put(EMPLOYEE_NAME, employee.getName());
        contentValues.put(EMPLOYEE_DATE_OF_BIRTH, employee.getDateOB());
        contentValues.put(EMPLOYEE_HOMETOWN, employee.getHometown());
        contentValues.put(EMPLOYEE_DEPARTMENT, employee.getDepartment());

        sqLiteDatabase.update(TABLE_EMPLOYEE, contentValues, EMPLOYEE_ID + " = ?", selectionArgs);
        sqLiteDatabase.close();
    }

    public void removeEmployee(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] selectionArgs = new String[]{employee.getId()};
        sqLiteDatabase.delete(TABLE_EMPLOYEE, EMPLOYEE_ID + " = ?", selectionArgs);
        sqLiteDatabase.close();
    }

    public boolean isEmployeeExist(String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = new String[]{id};
        Cursor cursor = sqLiteDatabase.query(TABLE_EMPLOYEE, null, EMPLOYEE_ID + " = ?", selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return cursorCount > 0;
    }

    public ArrayList<String> getAllDepartmentTitle() {
        ArrayList<String> departments = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_DEPARTMENT;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(1).trim();
            departments.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return departments;
    }

    public Department getDepartmentByID(String idKey) {
        Department department = new Department();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = new String[]{idKey};
        Cursor cursor = sqLiteDatabase.query(TABLE_DEPARTMENT, null, DEPARTMENT_ID + " = ?", selectionArgs, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);

                department = new Department(id, name, description);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return department;
    }

    public void addDepartment(Department department) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DEPARTMENT_ID, department.getId());
        contentValues.put(DEPARTMENT_NAME, department.getName());
        contentValues.put(DEPARTMENT_DESCRIPTION, department.getDescription());

        sqLiteDatabase.insert(TABLE_DEPARTMENT, null, contentValues);
        sqLiteDatabase.close();
    }

    public void editDepartment(Department department) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] selectionArgs = new String[]{department.getId()};

        contentValues.put(DEPARTMENT_NAME, department.getName());
        contentValues.put(DEPARTMENT_DESCRIPTION, department.getDescription());

        sqLiteDatabase.update(TABLE_DEPARTMENT, contentValues, DEPARTMENT_ID + " = ?", selectionArgs);
        sqLiteDatabase.close();
    }

    public void removeDepartment(Department department) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] selectionArgs = new String[]{department.getId()};
        sqLiteDatabase.delete(TABLE_DEPARTMENT, DEPARTMENT_ID + " = ?", selectionArgs);
        sqLiteDatabase.close();
    }

    public boolean isDepartmentExist(String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = new String[]{id};
        Cursor cursor = sqLiteDatabase.query(TABLE_DEPARTMENT, null, DEPARTMENT_ID + " = ?", selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return cursorCount > 0;
    }
}
