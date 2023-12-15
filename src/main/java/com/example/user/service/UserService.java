package com.example.user.service;

import com.example.user.generator.IdGenerator;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.example.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private JwtUtil jwtUtil;

    public User createUser(User user) {
        // Generate SHA1 hash for the email
        String sha1Hash = idGenerator.generateSHA1Hash(user.getEmail());

        user.setId(sha1Hash);

        // Save the user to get the auto-generated ID
        user = userRepository.save(user);

        Map<String, Object> jwtClaims = new HashMap<>();
        jwtClaims.put("customClaimKey", user);
        String accessToken = jwtUtil.generateToken(user.getId(), jwtClaims);



        // Set the JWT Token in the User entity
        user.setAccessToken(accessToken);

        // Save the user with the JWT Token
        userRepository.save(user);

        return user;
    }

    public User getUserById(String userId, String accessToken) {
        Optional<User> optionalUser = userRepository.findById(String.valueOf(userId));
        if (optionalUser.isPresent()) {
            if(!optionalUser.get().isMarketingConsent()){
                optionalUser.get().setEmail(null);
            }
            return optionalUser.get();
        }
        return null;
    }
}
