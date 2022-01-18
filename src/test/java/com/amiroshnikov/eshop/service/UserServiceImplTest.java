package com.amiroshnikov.eshop.service;

import com.amiroshnikov.eshop.dao.UserRepository;
import com.amiroshnikov.eshop.domain.User;
import com.amiroshnikov.eshop.dto.UserDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private PasswordEncoder encoder;
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {System.out.println("Before all tests!");}

    @BeforeEach
    void setUp() {
        System.out.println("Before each test!");
        encoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, encoder);
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test!");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all tests!");
    }

    @Test
    void checkFindByName() {
        //have
        String name = "Alexey";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByName(name);

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void checkFindByNameExact() {
        //have
        String name = "Alexey";
        User expectedUser = User.builder().id(1L).name(name).build();

        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByName(name);
        User randomUser = userService.findByName(UUID.randomUUID().toString());

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

        Assertions.assertNotNull(randomUser);
    }

    @Test
    void checkSaveIncorrectPassword() {
        //have
        UserDto userDto = UserDto.builder()
                .password("password")
                .matchingPassword("anotherPass")
                .build();
        //execute
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                userService.save(userDto);
            }
        });
    }

    @Test
    void checkSave() {
        //have
        UserDto userDto = UserDto.builder()
                .username("name")
                .email("email@mail.com")
                .password("password")
                .matchingPassword("password")
                .build();

        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("password");

        //execute
        boolean result = userService.save(userDto);

        //check
        Assertions.assertTrue(result);
        Mockito.verify(encoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());
    }
}