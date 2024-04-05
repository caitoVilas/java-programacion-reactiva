package com.caito.apirestreactivemongo.repository;

import com.caito.apirestreactivemongo.documents.Contact;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ContactRepository extends ReactiveMongoRepository<Contact, String> {
    Mono<Contact> findByEmail(String email);
    Mono<Contact> findAllByTelOrName(String telOrName);
}
