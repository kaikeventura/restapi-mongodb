package br.com.kaikeventura.restmongodb.service.impl;

import br.com.kaikeventura.restmongodb.MockObjects;
import br.com.kaikeventura.restmongodb.dto.AddressDTO;
import br.com.kaikeventura.restmongodb.dto.UserDTO;
import br.com.kaikeventura.restmongodb.error.exception.DocumentNumberAlreadyRegisteredException;
import br.com.kaikeventura.restmongodb.error.exception.UserNotFoundException;
import br.com.kaikeventura.restmongodb.model.User;
import br.com.kaikeventura.restmongodb.repository.UserRepository;
import br.com.kaikeventura.restmongodb.util.UserUtil;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserUtil userUtil;

    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, userUtil);
        MockObjects.objects();
    }

    @Test
    public void save() {
        UserDTO userDTO = new UserDTO(
                "Maicon",
                "Douglas",
                25,
                "68010794090",
                new AddressDTO(
                        "Rua Jujuba das Pegadas, 234",
                        "16900016",
                        "Suzano",
                        "São Paulo"
                )
        );
        User expectedUser = Fixture.from(User.class).gimme("uniqueUser");
        when(userRepository.save(userUtil.toUser(userDTO))).thenReturn(expectedUser);

        User actualUser = userService.save(userDTO);

        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(expectedUser);
    }

    @Test
    public void findAll() {
        List<User> expectedUsers = Fixture.from(User.class).gimme(5, "manyUsers");
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findAll();

        assertThat(actualUsers).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedUsers);
    }

    @Test
    public void findByName() {
        List<User> expectedUsers = Fixture.from(User.class).gimme(1, "uniqueUser");
        when(userRepository.findByNameLikeIgnoreCase("Maicon")).thenReturn(expectedUsers);

        List<User> actualUsers = userService.findByName("Maicon");

        assertThat(actualUsers).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedUsers);
    }

    @Test
    public void findByDocumentNumber() {
        User expectedUser = Fixture.from(User.class).gimme("uniqueUser");
        when(userRepository.findByDocumentNumber("68010794090")).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findByDocumentNumber("68010794090");

        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(expectedUser);
    }

    @Test
    public void update() {
        UserDTO userDTO = new UserDTO(
                "Maicon",
                "Douglas",
                25,
                "68010794090",
                new AddressDTO(
                        "Rua Jujuba das Pegadas, 234",
                        "16900016",
                        "Suzano",
                        "São Paulo"
                )
        );
        User expectedUser = Fixture.from(User.class).gimme("uniqueUser");
        Optional<User> optionalExpectedUser = Optional.of(expectedUser);
        when(userRepository.findByDocumentNumber("68010794090")).thenReturn(optionalExpectedUser);
        when(userRepository.save(optionalExpectedUser.get())).thenReturn(expectedUser);

        User actualUser = userService.update(userDTO, "68010794090");

        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(expectedUser);
    }

    @Test
    public void updateAddress() {
        AddressDTO addressDTO = new AddressDTO(
                "Rua Jujuba das Pegadas, 234",
                "16900016",
                "Suzano",
                "São Paulo"
        );
        User expectedUser = Fixture.from(User.class).gimme("uniqueUser");
        Optional<User> optionalExpectedUser = Optional.of(expectedUser);
        when(userRepository.findByDocumentNumber("68010794090")).thenReturn(optionalExpectedUser);
        when(userRepository.save(optionalExpectedUser.get())).thenReturn(expectedUser);

        User actualUser = userService.updateAddress(addressDTO, "68010794090");

        assertThat(actualUser).isEqualToComparingFieldByFieldRecursively(expectedUser);
    }

    @Test
    public void delete() {
        User expectedUser = Fixture.from(User.class).gimme("uniqueUser");
        when(userRepository.findByDocumentNumber("68010794090")).thenReturn(Optional.of(expectedUser));

        userService.delete("68010794090");
    }

    @Test(expected = DocumentNumberAlreadyRegisteredException.class)
    public void documentNumberAlreadyRegisteredException() {
        UserDTO userDTO = new UserDTO(
                "Maicon",
                "Douglas",
                25,
                "68010794090",
                new AddressDTO(
                        "Rua Jujuba das Pegadas, 234",
                        "16900016",
                        "Suzano",
                        "São Paulo"
                )
        );
        User expectedUser = Fixture.from(User.class).gimme("uniqueUser");
        when(userRepository.findByDocumentNumber("68010794091")).thenReturn(Optional.of(expectedUser));

        userService.update(userDTO, "68010794091");
    }

    @Test(expected = UserNotFoundException.class)
    public void userNotFoundException() {
        when(userRepository.findByDocumentNumber("68010794090")).thenReturn(Optional.empty());

        userService.findByDocumentNumber("68010794090");
    }
}