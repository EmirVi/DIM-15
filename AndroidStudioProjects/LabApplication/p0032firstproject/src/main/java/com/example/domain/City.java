package com.example.domain;

public class City {
    private String name;
    private int population;
    private double area;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getPopulation() { return population; }

    public void setPopulation(int population) { this.population = population; }

    public double getArea() { return area; }

    public void setArea(int area) { this.area = area; }

    public City(String name, int population, double area){
        this.name = name;
        this.population = population;
        this.area = area;
    }
}