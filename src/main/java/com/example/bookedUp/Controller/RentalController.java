package com.example.bookedUp.Controller;

import com.example.bookedUp.Model.Rental;
import com.example.bookedUp.Service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/{type}")
    public Rental createRental(@PathVariable String type, @RequestParam String address, @RequestParam double price) {
        return rentalService.createRental(type, address, price);
    }
}
