package dtos;

import entities.Booking;
import entities.Car;
import entities.WashingAssistant;

import java.util.ArrayList;
import java.util.List;

public class BookingDTO {
    private long id;
    private int dateAndTime;
    private int duration;
    private long carId;

    public BookingDTO(long id, int dateAndTime, int duration) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
    }

    public BookingDTO(Booking b) {
        this.id = b.getId();
        this.dateAndTime = b.getDateAndTime();
        this.duration = b.getDuration();
    }

    public BookingDTO(int dateAndTime, int duration,Car car, WashingAssistant washingAssistant) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;

    }

    public static List<BookingDTO> getDtos(List<Booking> bs){
        List<BookingDTO> bdtos = new ArrayList<>();
        bs.forEach(b->bdtos.add(new BookingDTO(b)));
        return bdtos;
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
        this.duration = duration;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", duration=" + duration +
                ", carId=" + carId +
                '}';
    }
}
