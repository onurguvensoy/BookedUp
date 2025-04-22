package com.example.bookedUp.Controller;

import com.example.bookedUp.Model.User;
import com.example.bookedUp.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/{type}")
    public User createUser(@PathVariable String type, @RequestBody User user) {
        return userService.createUser(type, user.getUsername(), user.getEmail());
    }
}
