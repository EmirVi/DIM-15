package com.example.p0031firstproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.domain.Employee;

public class EmployeeTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Employee emp = new Employee();
        emp.setEmpId(101);
        emp.setName("Jane Smith");
        emp.setSalary(120_345.27);
        emp.setSsn("012-34-5678");

        System.out.println("Employee ID: "+emp.getEmpId());
        System.out.println("Employee Name: "+emp.getName());
        System.out.println("Employee Soc Sec # "+emp.getSsn());
        System.out.println("Employee salary: "+emp.getSalary());
    }
}