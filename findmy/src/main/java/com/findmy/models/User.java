package com.findmy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_phone_number"))

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length =45 )
    private Long id;

    @Column(name="user_email", length = 255)
    private String username;

    @Column(name="user_full_name", length = 255)
    private String userFullName;

    @Column(name="user_password", length = 255)
    private String password;

//    @Column(name="user_email", length = 255)
//    private String email;

    @Column(name = "user_phone_number", length = 255)
    private String phone;

    @Column(name="user_enable")
    private boolean enable=true;

    @Column(name="user_profile")
    private String profile;

// user have many roles
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set=new HashSet<>();

        this.userRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRole_name()));
        });

        return null;
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
