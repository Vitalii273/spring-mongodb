package com.telran.springmongodb.controller.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PersonDto {
    private int id;
    private String name;
    private String email;
    private String phone;
}
