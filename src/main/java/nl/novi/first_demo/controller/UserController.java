package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.User;
import nl.novi.first_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/users/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser (@PathVariable String userName) {
        return userService.getUser(userName);
    }

    @PostMapping(value = "/users")
    public ResponseEntity addUser(@RequestBody User user){
        String newUserName = userService.addUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserName).toUri();

        return ResponseEntity.created(location).body("Added " + newUserName);
    }

    @DeleteMapping(value = "/users/{userName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser (@PathVariable String userName){
        userService.deleteUser(userName);
        return "Removed";
    }

    @PutMapping(value = "/users/{userName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updatUser (@PathVariable String userName, @RequestBody User user) {
        userService.updateUser(userName, user);
        return "Updated";
    }

    @PatchMapping(value = "/users/{userName}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String setNewPassword (@PathVariable String userName, @RequestBody String newPassword) {
        userService.setPassword(userName, newPassword);
        return "Password changed.";
    }
}
