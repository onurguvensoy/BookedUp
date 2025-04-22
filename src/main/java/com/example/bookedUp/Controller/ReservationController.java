package com.example.bookedUp.Controller;

import com.example.bookedUp.Facade.ReservationFacade;
import com.example.bookedUp.Model.Reservation;
import com.example.bookedUp.Model.Rental;
import com.example.bookedUp.Model.User;
import com.example.bookedUp.Model.Guest; // Import concrete class
import com.example.bookedUp.Model.Villa; // Import concrete class
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationFacade reservationFacade;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestParam Long userId,
                                                         @RequestParam Long rentalId,
                                                         @RequestParam int days) {
        // Instantiate concrete user and rental classes
        User user = new Guest();  // Instantiate concrete Guest class
        user.setId(userId);

        Rental rental = new Villa();  // Instantiate concrete Villa class
        rental.setId(rentalId);

        Reservation reservation = reservationFacade.createReservation(user, rental, days);

        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserve(@RequestParam Long userId,
                                               @RequestParam Long rentalId,
                                               @RequestParam int days) {
        // Instantiate concrete user and rental classes
        User user = new Guest();  // Change this if you want to use other types
        user.setId(userId);

        Rental rental = new Villa();  // Change this if you want to use other types
        rental.setId(rentalId);

        Reservation reservation = reservationFacade.reserve(user, rental, days);

        return ResponseEntity.ok(reservation);
    }
}
