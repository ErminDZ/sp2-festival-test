package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
    public class WashingAssistant implements Serializable {
        private static final long SerialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private long id;
        private String name;
        private String primaryLanguage;
        private int yearsOfExp;
        private int pricePrHour;

        public WashingAssistant() {
        }

        public WashingAssistant(String name, String primaryLanguage, int yearsOfExp, int pricePrHour) {
            this.name = name;
            this.primaryLanguage = primaryLanguage;
            this.yearsOfExp = yearsOfExp;
            this.pricePrHour = pricePrHour;
        }

        @ManyToMany(mappedBy = "washingAssistants")
        private List<Booking> bookings = new ArrayList<Booking>();

        public static long getSerialVersionUID() {
            return SerialVersionUID;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrimaryLanguage() {
            return primaryLanguage;
        }

        public void setPrimaryLanguage(String primaryLanguage) {
            this.primaryLanguage = primaryLanguage;
        }

        public int getYearsOfExp() {
            return yearsOfExp;
        }

        public void setYearsOfExp(int yearsOfExp) {
            this.yearsOfExp = yearsOfExp;
        }

        public int getPricePrHour() {
            return pricePrHour;
        }

        public void setPricePrHour(int pricePrHour) {
            this.pricePrHour = pricePrHour;
        }

        public List<Booking> getBookings() {
        return bookings;
    }

        public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        if(!booking.getWashingAssistants().contains(this)){
            booking.getWashingAssistants().add(this);
        }
    }

    @Override
        public String toString() {
            return "WashingAssistant{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", primaryLanguage='" + primaryLanguage + '\'' +
                    ", yearsOfExp=" + yearsOfExp +
                    ", pricePrHour=" + pricePrHour +
                    '}';
        }


}

