package com.example.springauthapi.entities;

import com.example.springauthapi.roles.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users_auth")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "encoded_password")
    @JsonIgnore
    private String encodedPassword;

    @Column(name = "role")
    @JsonIgnore
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO check it
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return encodedPassword;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
