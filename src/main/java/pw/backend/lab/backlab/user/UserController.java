package pw.backend.lab.backlab.user;

import pw.backend.lab.backlab.user.exception.FileStorageException;

import pw.backend.lab.backlab.user.exception.MyUserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.backend.lab.backlab.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


   

    @PostMapping(path = "")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {

       
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @GetMapping(path = "")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userRepository.findById(userId).orElseThrow(() -> new MyUserNotFoundException("User not found with id " + userId)));
    }

    @GetMapping(path = "/login/{login}")
    public ResponseEntity<List<User>> getUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(userRepository.findAllByLogin(login));
    }

    @PutMapping(path = "")
    public ResponseEntity<User> updateWholeUser(@RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok().body(userRepository.save(updatedUser));
    }

    @PatchMapping(path = "")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok().body(userService.updateUser(updatedUser));
    }
}
