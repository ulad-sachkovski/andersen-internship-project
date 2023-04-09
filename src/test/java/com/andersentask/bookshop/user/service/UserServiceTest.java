package com.andersentask.bookshop.user.service;

import com.andersentask.bookshop.user.entities.User;
import com.andersentask.bookshop.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static com.andersentask.bookshop.user.enums.Role.ROLE_USER;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private final User user = User.builder()
            .email("example@gmail.com")
            .password("password")
            .firstName("exampleFirstName")
            .lastName("exampleLastName")
            .role(ROLE_USER)
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void registrationNewUser() {
        when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(Optional.empty());

        assertTrue(userService.registration(user));
        verify(userRepository, times(1)).findByEmailIgnoreCase(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void registrationExistingUser() {
        when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(Optional.of(user));

        assertFalse(userService.registration(user));
        verify(userRepository, times(1)).findByEmailIgnoreCase(user.getEmail());
    }

    @Test
    void findByEmailExistingUser() {
        when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(Optional.of(user));

        assertEquals(user, userService.findByEmail(user.getEmail()));
        verify(userRepository, times(1)).findByEmailIgnoreCase(user.getEmail());
    }

    @Test
    void findByEmailMissingUser() {
        when(userRepository.findByEmailIgnoreCase(user.getEmail())).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> userService.findByEmail(user.getEmail()));
        String expectedMessage = "User is missing with email:" + user.getEmail();
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

        verify(userRepository, times(1)).findByEmailIgnoreCase(user.getEmail());
    }
}