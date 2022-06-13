package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Car.deleteCar", query = "DELETE from Car")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int regNumber;
    private String brand;
    private String make;
    private int year;

    public Car() {
    }

    public Car(int regNumber, String brand, String make, int year) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking){
        this.bookings.add(booking);
        if (booking.getCar() != this){
            booking.setCar(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", regNumber=" + regNumber +
                ", brand='" + brand + '\'' +
                ", make='" + make + '\'' +
                ", year=" + year +
                '}';
    }
}
