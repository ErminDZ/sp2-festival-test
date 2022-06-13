package facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

//import errorhandling.RenameMeNotFoundException;
import dtos.BookingDTO;
import dtos.CarDTO;
import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.Car;
import entities.WashingAssistant;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BookingDTO getBookingById(long bookingId) {
        EntityManager em = emf.createEntityManager();
        Booking b = em.find(Booking.class, bookingId);
        return new BookingDTO(b);
    }

    public CarDTO getCarById(long carId) {
        EntityManager em = emf.createEntityManager();
        Car c = em.find(Car.class, carId);
        return new CarDTO(c);
    }

    public long getCarCount() {
        EntityManager em = getEntityManager();
        try{
            long carCount = (long)em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return carCount;
        }finally{
            em.close();
        }
    }

    public List<WashingAssistantDTO> getAllWashingAssistants(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<WashingAssistant> query = em.createQuery("SELECT w FROM WashingAssistant w", WashingAssistant.class);
        List<WashingAssistant> ws = query.getResultList();
        System.out.println(ws);
        return WashingAssistantDTO.getDtos(ws);
    }

    public List<BookingDTO> getAllBookings(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b", Booking.class);
        List<Booking> bs = query.getResultList();
        System.out.println(bs);
        return BookingDTO.getDtos(bs);
    }

    public BookingDTO createBooking(BookingDTO b) {
        Booking bo = new Booking(b.getDateAndTime(),b.getDuration(),new Car(),new WashingAssistant());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookingDTO(bo);
    }

    public BookingDTO assignWashAss(long bookingId, long washAssId) {
        EntityManager em = emf.createEntityManager();
        try {
            Booking b = em.find(Booking.class, bookingId);
            WashingAssistant w = em.find(WashingAssistant.class, washAssId);

            b.setWashingAssistant(w);
            w.addBooking(b);

            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();
            return new BookingDTO(b);
        } finally {
            em.close();
        }
    }

    public WashingAssistantDTO createWashingAssistant(WashingAssistantDTO wa) {
        WashingAssistant w = new WashingAssistant(wa.getName(), wa.getPrimaryLanguage(), wa.getYearsOfExp(),wa.getPricePrHour());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(w);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println("hej" + w);
        return new WashingAssistantDTO(w);
    }

    public BookingDTO editBooking(BookingDTO bookingDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            Booking b = em.find(Booking.class, bookingDTO.getId());
            Car c = em.find(Car.class, bookingDTO.getCarId());


            b.setDateAndTime(bookingDTO.getDateAndTime());
            b.setDuration(bookingDTO.getDuration());
            b.setCar(c);

            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();
            return new BookingDTO(b);

        }finally {
            em.close();
        }
    }


    public CarDTO editCar(CarDTO c) throws exceptions.CarNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Car car = em.find(Car.class, c.getId());
            if (car == null) {
                throw new exceptions.CarNotFoundException(String.format("Car with id: (%d) not found", c.getId()));
            } else {
                car.setBrand(c.getBrand());
                car.setMake(c.getMake());
                car.setRegNumber(c.getRegNumber());
                car.setYear(c.getYear());

                em.getTransaction().begin();
                em.merge(car);
                em.getTransaction().commit();
            }
            return new CarDTO(car);
        } finally {
            em.close();
        }
    }



    /*
    public CarDTO editCar(CarDTO carDTO) {

        EntityManager em = emf.createEntityManager();
        try {
            Car c = em.find(Car.class, carDTO.getId());

            c.setBrand(carDTO.getBrand());
            c.setMake(carDTO.getMake());
            c.setRegNumber(carDTO.getRegNumber());
            c.setYear(carDTO.getYear());

            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return new CarDTO(c);

        }finally {
            em.close();
        }
    }
     */

    public Response deleteBooking(long id) {
        EntityManager em= emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE FROM Booking b WHERE b.id = :id").setParameter("id", id);
            q.executeUpdate();
            em.getTransaction().commit();
            return Response.ok().build();
        } finally {
            em.close();
        }
    }

    
    public static void main(String[] args) throws exceptions.CarNotFoundException {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        //fe.getAllWashingAssistants();
        //fe.getAllBookings();

        //BookingDTO bd = new BookingDTO(121,121,new Car(),new WashingAssistant());
        //fe.createBooking(bd);

        //fe.deleteBooking(1);

        //fe.assignWashAss(1,2);

        //WashingAssistantDTO wad = new WashingAssistantDTO("Ermin","Dansk",12,12);
        //fe.createWashingAssistant(wad);

//        BookingDTO bd = fe.getBookingById(2);
//        bd.setDateAndTime(1234);
//        bd.setDuration(51231);
//        bd.setCarId(2);
//        fe.editBooking(bd);
//        System.out.println("Testing editBoat" + "\n" + bd);

        CarDTO cc = fe.getCarById(2);
        cc.setBrand("hej");
        cc.setMake("igen");
        cc.setYear(123);
        cc.setRegNumber(231);
        fe.editCar(cc);
        System.out.println("Testing editBoat" + "\n" + cc);
    }
}
