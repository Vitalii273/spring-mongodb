package com.telran.springmongodb.data.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Document(collection = "persons")
public class Person {
    @Id
    private int id; //just id <-----
    private String name;
    private String email;
    private String phone;

}
