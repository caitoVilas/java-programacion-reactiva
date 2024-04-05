package com.caito.apirestreactivemongo.functional;

import com.caito.apirestreactivemongo.documents.Contact;
import com.caito.apirestreactivemongo.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.*;

@Component
@RequiredArgsConstructor
public class ContacHandler {
    private final ContactRepository contactRepository;

    private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    //listar contactos
    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contactRepository.findAll(), Contact.class);
    }

    //ver un contacto
    public Mono<ServerResponse> getOne(ServerRequest request){
        String id = request.pathVariable("id");
        return contactRepository.findById(id)
                .flatMap(contact -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact))
                        .switchIfEmpty(response404));
    }

    //buscar por email
    public Mono<ServerResponse> getByEmail(ServerRequest request){
        String email = request.pathVariable("email");
        return contactRepository.findByEmail(email)
                .flatMap(contact -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact))
                        .switchIfEmpty(response404));
    }

    //guardar contacto
    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Contact> contact = request.bodyToMono(Contact.class);
        return contact
                .flatMap(contact1 -> contactRepository.save(contact1)
                        .flatMap(saved -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(saved))))
                .switchIfEmpty(response406);
    }

    //actualizar contacto
    public Mono<ServerResponse> update(ServerRequest request){
        Mono<Contact> contactMono = request.bodyToMono(Contact.class);
        String id = request.pathVariable("id");
        Mono<Contact> updated = contactMono.flatMap(contact ->
            contactRepository.findById(id)
                    .flatMap(newContact -> {
                        newContact.setId(id);
                        newContact.setName(contact.getName());
                        newContact.setEmail(contact.getEmail());
                        newContact.setTel(contact.getTel());
                        return contactRepository.save(newContact);
                    }));
        return contactMono.flatMap(contact1 -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact1)))
                .switchIfEmpty(response404);
    }

    //eliminar contacto
    public Mono<ServerResponse> delete(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Void> deleted = contactRepository.deleteById(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleted, Void.class);
    }
}
