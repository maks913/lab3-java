package ua.edu.nuos.lab3java.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "check_in")
    private LocalDate checkIn;

    @Column(name = "check_out")
    private LocalDate checkOut;

    @NotNull
    @Column(name = "room_number", length = 10)
    private String roomNumber;

    @NotNull
    @Column(name = "client_name")
    private String clientName;

    @NotNull
    @Column(name = "price_per_day")
    private Double pricePerDay;

}