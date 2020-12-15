package ul.lviv.iot.springjpa.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ul.lviv.iot.springjpa.dto.UserDto;
import ul.lviv.iot.springjpa.mapper.UserMapper;
import ul.lviv.iot.springjpa.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        var allUsers = userService.findAllUsers().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
        return (CollectionUtils.isEmpty(allUsers)
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allUsers, HttpStatus.OK));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id) {
        var user = userService.findUserById(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK));
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        var newUser = userService.createUser(userMapper.toEntity(userDto));
        return newUser == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(userMapper.toDto(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(final @PathVariable("id") Long id) {
        userService.deleteUser(id);
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(final @PathVariable("id") Long id,
                                              final @RequestBody UserDto userDto) {
        var updatedUser = userService.updateUser(id, userMapper.toEntity(userDto));
        return (id == null
                ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(userMapper.toDto(updatedUser), HttpStatus.OK));
    }
}
