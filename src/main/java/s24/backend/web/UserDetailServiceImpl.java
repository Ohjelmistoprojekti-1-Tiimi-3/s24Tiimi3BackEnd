package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import s24.backend.domain.AppUser;
import s24.backend.domain.AppUserRepository;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    // Automatically inject the repository for database access
    @Autowired
    private AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         // Fetch the user from the database using the username
        AppUser curruser = repository.findByUsername(username);
         // If no user is found, throw an exception
        if (curruser == null) {
            throw new UsernameNotFoundException("Käyttäjää ei löytynyt käyttäjänimellä: " + username);
        }

        // Return a UserDetails implementation with the user's details:
        // - Username
        // - Password hash (used for authentication)
        // - Roles/authorities (used for authorization)

        return new org.springframework.security.core.userdetails.User(
            curruser.getUsername(),
            curruser.getPasswordhash(),
            Collections.singleton(new SimpleGrantedAuthority(curruser.getRole()))
        );
    }
}

