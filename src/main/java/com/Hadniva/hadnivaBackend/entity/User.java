package com.Hadniva.hadnivaBackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "Appuser")
public class User implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private String name;
    private String password;
    private String profilePictureUrl;

    @Convert(converter = MapToStringConverter.class)
    private Map<String, Object> attributes;

    // Constructor with parameters
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
    

    public User(Long id, String email, String name, String password, String profilePictureUrl,
			Map<String, Object> attributes) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.profilePictureUrl = profilePictureUrl;
		this.attributes = attributes;
	}


	// Default constructor
    public User() {
    }

    // Implementing UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return authorities for the user, this can be customized as per role management
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
