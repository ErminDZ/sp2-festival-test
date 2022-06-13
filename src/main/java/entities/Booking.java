package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    private int dateAndTime;
    //Kan ændres til automatisk at indsætte date&time
    private int duration;

    public Booking() {
    }

    public Booking(int dateAndTime, int duration) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;
    }

    public Booking(int dateAndTime, int duration, Car car, WashingAssistant washingAssistant) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.car = car;
        this.washingAssistants = null;

    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;

    @ManyToMany
    @JoinTable(
            name = "BOOKING_WASHINGASSISTANT",
            joinColumns = @JoinColumn(name = "BOOKING_ID",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "WASHINGASSISTANT_ID",referencedColumnName = "ID"))
    private List<WashingAssistant> washingAssistants = new ArrayList<WashingAssistant>();



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(int dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        duration = duration;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        if (!car.getBookings().contains(this)){
            car.getBookings().add(this);
        }
    }

    public List<WashingAssistant> getWashingAssistants() {
        return washingAssistants;
    }

    public void setWashingAssistants(List<WashingAssistant> washingAssistants) {
        this.washingAssistants = washingAssistants;
    }

    public void setWashingAssistant(WashingAssistant washingAssistant) {
        if (!washingAssistant.getBookings().contains(this)) {
            washingAssistant.getBookings().add(this);
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", duration=" + duration +
                '}';
    }


}
