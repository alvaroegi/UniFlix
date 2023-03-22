package com.example.uniflix.RestController;

import com.example.uniflix.Entities.User;
import com.example.uniflix.ServiceControllers.UserServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserRestController {
    @Autowired
    UserServiceController usersService;

    @GetMapping("/usuario")
    public Collection<User> todosAnuncios() {
        return usersService.getAllUsers();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User u = usersService.getUser(id);
        if (u != null) {
            return new ResponseEntity<>(u, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User u) {
        usersService.addUser(u);
        return u;
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        User u = usersService.deleteUser(id);
        if(u!=null) {
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        User u = usersService.getUser(id);
        if (u != null) {
            updatedUser.setId(id);
            usersService.updateUser(id, updatedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
