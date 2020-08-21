package br.com.kaikeventura.restmongodb.service;

import br.com.kaikeventura.restmongodb.dto.AddressDTO;
import br.com.kaikeventura.restmongodb.dto.UserDTO;
import br.com.kaikeventura.restmongodb.model.User;

import java.util.List;

public interface UserService {
    User save(UserDTO userDTO);
    List<User> findAll();
    List<User> findByName(String name);
    User findByDocumentNumber(String documentNumber);
    User update(UserDTO userDTO, String documentNumber);
    User updateAddress(AddressDTO addressDTO, String documentNumber);
    void delete(String documentNumber);
}
