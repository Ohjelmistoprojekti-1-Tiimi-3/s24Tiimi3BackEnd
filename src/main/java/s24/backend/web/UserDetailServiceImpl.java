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

    @Autowired
    private AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("Käyttäjää ei löytynyt käyttäjänimellä: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
            curruser.getUsername(),
            curruser.getPasswordhash(),
            Collections.singleton(new SimpleGrantedAuthority(curruser.getRole()))
        );
    }
}

