package s24.backend.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s24.backend.domain.AppUser;
import s24.backend.domain.AppUserRepository;

@RestController
@RequestMapping("/api")
public class RestAppUserController {

    @Autowired
    private AppUserRepository appuserrepo;

    // Get a specific AppUser by username
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("appuser/{username}")
	public Optional<AppUser> getAppUserByUsername(@PathVariable("username") String username) {
		return Optional.of((appuserrepo.findByUsername(username)));
	}

    // Adding a new AppUser
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addappuser")
    public AppUser newAppUser(@RequestBody AppUser newAppUser) {
        
        return appuserrepo.save(newAppUser);
    }

    // Deleting an AppUser
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteappuser/{id}")
    public Iterable<AppUser> deleteAppUser(@PathVariable("id") Long userid) {

        appuserrepo.deleteById(userid);
        return appuserrepo.findAll();
    }

}
