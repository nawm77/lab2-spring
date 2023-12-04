package ru.ilya.lab2_spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ilya.lab2_spring.model.User;
import ru.ilya.lab2_spring.repository.UserRepository;

import java.text.MessageFormat;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(email);
        if( user == null) {
            throw new UsernameNotFoundException(MessageFormat.format("User {0} doesn't exist", email));
        }
        return SecurityUser.fromUser(user);
    }
}