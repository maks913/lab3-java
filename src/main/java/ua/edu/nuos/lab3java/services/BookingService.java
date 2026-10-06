package ua.edu.nuos.lab3java.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import ua.edu.nuos.lab3java.data.Booking;
import java.util.List;

@Named
@ApplicationScoped
public class BookingService {

    @PersistenceContext
    private EntityManager em;

    @Getter
    @Setter
    private Booking booking = new Booking();

    @Getter
    @Setter
    private Booking editingBooking;

    public Booking getBookingById(int bookingId) {
        return em.find(Booking.class, bookingId);
    }

    public void edit(int bookingId) {
        editingBooking = getBookingById(bookingId);
    }

    public List<Booking> getAllBookings() {
        return em
                .createQuery("SELECT b FROM Booking b", Booking.class)
                .getResultList();
    }

    @Transactional
    public void createBooking() {
        if (booking.getCheckIn().isBefore(booking.getCheckOut())) {
            em.persist(booking);
            booking = new Booking();
        } else {
            throw new IllegalStateException();
        }
    }

    @Transactional
    public void updateBooking() {
        if (editingBooking != null && editingBooking.getCheckIn().isBefore(editingBooking.getCheckOut())) {
            em.merge(editingBooking);
        } else {
            throw new IllegalStateException();
        }
    }

    @Transactional
    public void deleteBooking(int bookingId) {
        Booking bookingToDelete = getBookingById(bookingId);

        if (bookingToDelete != null) {
            em.remove(bookingToDelete);
        }
    }
}

