package com.example.p0032firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.domain.City;
import com.example.domain.Citystate;
import com.example.domain.Megacity;
import com.example.domain.Municipal;

import java.util.ArrayList;

public class CityTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<City> cities = new ArrayList<City>();

        cities.add(new Megacity("Moscow", 13010112, 2561.5));
        cities.add(new Megacity("New York", 8804190, 1223.59));
        cities.add(new Municipal("Astrakhan", 520339, 208.7));
        cities.add(new Municipal("Kaliningrad", 431402, 223.03));
        cities.add(new Citystate("Singapore", 5637000, 733.1));
        cities.add(new Citystate("Vatican", 453, 0.49));

        for(City city: cities){
            printCity(city);
        }
    }

    private static void printCity(City city) {
        System.out.print("City: " + city.getName());
        System.out.print(" Population: " + city.getPopulation());
        System.out.print(" Area: " + city.getArea() + "km^2 \n");
    }
}