package com.jsrdev.TasksAPI.domain.models.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Boolean active;

    public User(AddUserRequest addUser, PasswordEncoder encoder) {
        this.login = addUser.login();
        this.password = passwordEncoder(addUser.password(), encoder);
        this.role = (addUser.role() == null) ? UserRole.USER : addUser.role();
        this.active = true;
    }

    //indicate type to user role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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

    public void updateData(UpdateUser updateUser, PasswordEncoder encoder) {
        if (updateUser.password() != null) {
            this.password = passwordEncoder(updateUser.password(), encoder);
        }
        if (updateUser.role() != null) {
            this.role = updateUser.role();
        }
        if (updateUser.active() != null) {
            this.active = updateUser.active();
        }
    }

    private String passwordEncoder(String password, PasswordEncoder encoder) {
        return encoder.encode(password);
    }

    public void deactivateUser() {
        this.active = false;
    }
}
