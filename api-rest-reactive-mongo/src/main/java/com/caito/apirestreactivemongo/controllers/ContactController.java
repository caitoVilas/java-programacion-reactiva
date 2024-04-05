package com.caito.apirestreactivemongo.controllers;

import com.caito.apirestreactivemongo.documents.Contact;
import com.caito.apirestreactivemongo.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;

    @GetMapping
    public Flux<Contact> getAll(){
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Contact>> getOne(@PathVariable String id){
        return contactRepository.findById(id)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public Mono<ResponseEntity<Contact>> getByEmail(@PathVariable String email){
        return contactRepository.findByEmail(email)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Mono<ResponseEntity<Contact>> save(@RequestBody Contact request){
        return contactRepository.insert(request)
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Contact>> update(@PathVariable String id, @RequestBody Contact request){
        return contactRepository.findById(id)
                .flatMap(contact -> {
                    request.setId(id);
                    return contactRepository.save(request);
                })
                .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return contactRepository.deleteById(id);
    }
}
