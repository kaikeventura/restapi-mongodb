package br.com.kaikeventura.restmongodb.controller;

import br.com.kaikeventura.restmongodb.dto.AddressDTO;
import br.com.kaikeventura.restmongodb.dto.UserDTO;
import br.com.kaikeventura.restmongodb.model.User;
import br.com.kaikeventura.restmongodb.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("users")
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDTO userDTO) {
        return new ResponseEntity(userService.save(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("users/all")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("users/name/{name}")
    public ResponseEntity<List<User>> findUsersByName(@PathVariable("name") @NotNull final String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @GetMapping("users/document-number/{documentNumber}")
    public ResponseEntity<User> findUsersByDocumentNumber(
            @PathVariable("documentNumber") @NotNull final String documentNumber
    ) {
        return ResponseEntity.ok(userService.findByDocumentNumber(documentNumber));
    }

    @PutMapping("users/{documentNumber}")
    public ResponseEntity<User> updateUser(
            @Valid @RequestBody final UserDTO userDTO,
            @PathVariable("documentNumber") @NotNull final String documentNumber
    ) {
        return ResponseEntity.ok(userService.update(userDTO, documentNumber));
    }

    @PatchMapping("users/address/{documentNumber}")
    public ResponseEntity<User> updateUserAddress(
            @Valid @RequestBody final AddressDTO addressDTO,
            @PathVariable("documentNumber") @NotNull final String documentNumber
    ) {
        return ResponseEntity.ok(userService.updateAddress(addressDTO, documentNumber));
    }

    @DeleteMapping("users/{documentNumber}")
    public ResponseEntity<User> deleteUser(@PathVariable("documentNumber") @NotNull final String documentNumber) {
        userService.delete(documentNumber);
        return ResponseEntity.noContent().build();
    }

}
