package br.com.kaikeventura.restmongodb.repository;

import br.com.kaikeventura.restmongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByDocumentNumber(String documentNumber);
    List<User> findByNameLikeIgnoreCase(String name);
}
