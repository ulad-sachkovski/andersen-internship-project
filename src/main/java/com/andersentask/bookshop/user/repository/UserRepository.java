package com.andersentask.bookshop.user.repository;

import com.andersentask.bookshop.common.AbstractCollectionRepository;
import com.andersentask.bookshop.user.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements AbstractCollectionRepository<User, Long> {

    private final List<User> users;

    private Long id;

    public UserRepository() {
        this.id = 1L;
        this.users = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        user.setId(id++);
        users.add(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    public Optional<User> findByEmailIgnoreCase(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
