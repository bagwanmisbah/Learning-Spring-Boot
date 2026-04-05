package com.misbah.jwt_security_demo.service;

import com.misbah.jwt_security_demo.dto.JwtAuthenticationResponse;
import com.misbah.jwt_security_demo.dto.SignInRequest;
import com.misbah.jwt_security_demo.dto.SignUpRequest;
import com.misbah.jwt_security_demo.entity.Role;
import com.misbah.jwt_security_demo.entity.User;
import com.misbah.jwt_security_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //function to register a new user and return a JWT token

    public JwtAuthenticationResponse signup(SignUpRequest request)
    {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        //here we created the entire USer object using lomboks builder and then we save it via user repo object
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        //our jwt service will then generate the token
        return JwtAuthenticationResponse.builder().token(jwt).build();

    }
    /**
     * Authenticates an existing user and returns a JWT token.
     */
    public JwtAuthenticationResponse signin(SignInRequest request) {
        // The AuthenticationManager will handle the validation of credentials.
        // If the credentials are bad, it will throw an exception.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // If we reach here, it means the user is authenticated successfully.
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}



