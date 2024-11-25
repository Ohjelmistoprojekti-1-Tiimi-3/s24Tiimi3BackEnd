package s24.backend.domain;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "passwordhash", nullable = false)
    private String passwordhash;

    @Column(name = "user_role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "appUser")
    private List<Customer> customers;

    public AppUser() {
    }

	public AppUser(String username, String passwordhash, String role) {
		super();
		this.username = username;
		this.passwordhash = passwordhash;
		this.role = role;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordhash() {
		return passwordhash;
	}

	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
