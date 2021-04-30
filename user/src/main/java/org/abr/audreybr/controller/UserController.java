package org.abr.audreybr.controller;

import org.abr.audreybr.dto.UserDTO;
import org.abr.audreybr.entity.User;
import org.abr.audreybr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping(path = "{id}")
    public User update(@PathVariable long id, @RequestBody User user)  {
        return userService.editUser(id,user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id)  {
        return userService.deleteUser(id);
    }


}
