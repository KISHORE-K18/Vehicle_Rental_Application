package com.example.Vehicle_Rental.service;

import com.example.Vehicle_Rental.auth.UserPrincipal;
import com.example.Vehicle_Rental.exception.UserNotFoundException;
import com.example.Vehicle_Rental.model.User;
import com.example.Vehicle_Rental.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final userRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        return new UserPrincipal(user);
    }
}
