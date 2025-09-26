package com.klu.CICDPROJECT.controller;

import com.klu.CICDPROJECT.dto.UserDTO;
import com.klu.CICDPROJECT.entity.User;
import com.klu.CICDPROJECT.repository.UserRepository;
import com.klu.CICDPROJECT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
    origins = "https://cicd-project-frontend-phi.vercel.app", // your frontend
    allowCredentials = "true"
)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElse(null);

        Map<String, Object> response = new HashMap<>();

        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            response.put("message", "Login successful");
            response.put("username", dbUser.getUsername());
            response.put("id", dbUser.getId());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }

    // Get user by username
    @GetMapping("/user/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO dto = new UserDTO(user.getId(), user.getUsername());
        return ResponseEntity.ok(dto);
    }
}
