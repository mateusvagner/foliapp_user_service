package com.foliapp.userservice.data.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String keyIdentifier;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles")
    private List<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyIdentifier() {
        return keyIdentifier;
    }

    public void setKeyIdentifier(String keyIdentifier) {
        this.keyIdentifier = keyIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
