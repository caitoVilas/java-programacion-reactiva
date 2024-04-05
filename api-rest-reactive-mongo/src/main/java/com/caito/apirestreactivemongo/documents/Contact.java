package com.caito.apirestreactivemongo.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    private String tel;
}
