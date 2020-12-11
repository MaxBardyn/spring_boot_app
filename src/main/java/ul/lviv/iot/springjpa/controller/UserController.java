package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.entity.User;
import ul.lviv.iot.springjpa.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        var allUsers = userService.findAllUsers();
        return (CollectionUtils.isEmpty(allUsers)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allUsers, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        var user = userService.findUserById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(user, HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        var newUser = userService.createUser(user);
        return newUser == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(final @PathVariable("id") Long id) {
        userService.deleteUser(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(final @PathVariable("id") Long id,
                                           final @RequestBody User user) {
        var updatedUser = userService.updateUser(id, user);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(updatedUser, HttpStatus.OK));
    }
}
