package br.com.kaikeventura.restmongodb.util;

import br.com.kaikeventura.restmongodb.dto.AddressDTO;
import br.com.kaikeventura.restmongodb.dto.UserDTO;
import br.com.kaikeventura.restmongodb.model.Address;
import br.com.kaikeventura.restmongodb.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public User toUser(final UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getAge(),
                userDTO.getDocumentNumber(),
                toAddress(userDTO.getAddressDTO())
        );
    }

    private Address toAddress(final AddressDTO addressDTO) {
        return new Address(
                addressDTO.getAddress(),
                addressDTO.getPostalCode(),
                addressDTO.getCity(),
                addressDTO.getState()
        );
    }

}
