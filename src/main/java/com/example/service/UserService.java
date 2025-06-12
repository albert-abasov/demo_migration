package com.example.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserService {
    private EntityManager entityManager;

    public UserService() {
        this.entityManager = Persistence.createEntityManagerFactory("my-persistence-unit").createEntityManager();
    }

    public void createAccount(String username, String password, String email) throws AccountCreationException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = new User(username, password, email);
            validateUser(user);
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new AccountCreationException("Failed to create account", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    private void validateUser(User user) throws AccountCreationException {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new AccountCreationException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new AccountCreationException("Password must be at least 6 characters long");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new AccountCreationException("Invalid email format");
        }
    }
}

class User {
    @NotBlank
    private String username;
    
    @Size(min = 6)
    private String password;
    
    @Email
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

class AccountCreationException extends Exception {
    public AccountCreationException(String message) {
        super(message);
    }

    public AccountCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}