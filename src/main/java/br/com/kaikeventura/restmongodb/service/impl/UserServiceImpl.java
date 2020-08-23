package br.com.kaikeventura.restmongodb.service.impl;

import br.com.kaikeventura.restmongodb.dto.AddressDTO;
import br.com.kaikeventura.restmongodb.dto.UserDTO;
import br.com.kaikeventura.restmongodb.error.exception.DocumentNumberAlreadyRegisteredException;
import br.com.kaikeventura.restmongodb.error.exception.UserNotFoundException;
import br.com.kaikeventura.restmongodb.model.User;
import br.com.kaikeventura.restmongodb.repository.UserRepository;
import br.com.kaikeventura.restmongodb.service.UserService;
import br.com.kaikeventura.restmongodb.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    @Override
    public User save(UserDTO userDTO) {
        verifyIfUserIsExists(userDTO.getDocumentNumber());

        return userRepository.save(userUtil.toUser(userDTO));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameLikeIgnoreCase(name);
    }

    @Override
    public User findByDocumentNumber(String documentNumber) {
        return getUser(documentNumber);
    }

    @Override
    public User update(UserDTO userDTO, String documentNumber) {
        if (!userDTO.getDocumentNumber().equals(documentNumber)) {
            verifyIfUserIsExists(documentNumber);
        }
        User user = getUser(documentNumber);
        BeanUtils.copyProperties(userDTO, user);

        return userRepository.save(user);
    }

    @Override
    public User updateAddress(AddressDTO addressDTO, String documentNumber) {
        User user = getUser(documentNumber);
        BeanUtils.copyProperties(addressDTO, user.getAddress());

        return userRepository.save(user);
    }

    @Override
    public void delete(String documentNumber) {
        userRepository.delete(getUser(documentNumber));
    }

    private User getUser(final String documentNumber) {
        final Optional<User> user = userRepository.findByDocumentNumber(documentNumber);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    private void verifyIfUserIsExists(String documentNumber) {
        userRepository.findByDocumentNumber(documentNumber).ifPresent(user -> {
            throw new DocumentNumberAlreadyRegisteredException();
        });
    }
}
