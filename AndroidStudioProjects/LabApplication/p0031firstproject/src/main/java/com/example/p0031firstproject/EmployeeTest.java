package com.example.p0031firstproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.domain.Employee;
import com.example.domain.Admin;
import com.example.domain.Director;
import com.example.domain.Engineer;
import com.example.domain.Manager;

public class EmployeeTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Engineer eng = new Engineer(101, "Jane Smith", "012-34-5678", 120_345.27);
        Manager mgr = new Manager(207, "Barbara Johnson", "054- 12- 2367", 109_501.36, "US Marketing");
        Admin adm = new Admin(304, "Bill Munroe", "108-23- 2367", 75_002.34);
        Director dir = new Director(12, "Susan Wheeler", "099- 45- 2340", 120_567.36, "Global Marketing", 1_000_000.00);

        printEmployee(eng);
        printEmployee(mgr);
        printEmployee(adm);
        printEmployee(dir);
    }

    private static void printEmployee(Employee emp) {
        System.out.println("Employee ID: " + emp.getEmpId());
        System.out.println("Employee Name: " + emp.getName());
        System.out.println("Employee Soc Sec # " + emp.getSsn());
        System.out.println("Employee salary: " + emp.getSalary());
    }
}