package dtos;

import entities.Car;

public class CarDTO {
    private long id;
    private int regNumber;
    private String brand;
    private String make;
    private int year;

    public CarDTO(long id, int regNumber, String brand, String make, int year) {
        this.id = id;
        this.regNumber = regNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public CarDTO(Car c) {
        this.id = c.getId();
        this.regNumber = c.getRegNumber();
        this.brand = c.getBrand();
        this.make = c.getMake();
        this.year = c.getYear();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", regNumber=" + regNumber +
                ", brand='" + brand + '\'' +
                ", make='" + make + '\'' +
                ", year=" + year +
                '}';
    }
}
