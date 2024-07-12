package com.Hadniva.hadnivaBackend.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Appuser")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    // Getters and setters for other fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
