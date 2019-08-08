package com.telran.springmongodb.controller;

import com.telran.springmongodb.controller.dto.CatalogCarDto;
import com.telran.springmongodb.controller.dto.PersonDto;
import com.telran.springmongodb.data.CarCatalogRepository;
import com.telran.springmongodb.data.PersonRepository;
import com.telran.springmongodb.data.document.CatalogCar;
import com.telran.springmongodb.data.document.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    CarCatalogRepository carCatalogRepository;

    @PostMapping("person")
    public void addPerson(@RequestBody PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Person with id [" + personDto.getId() + "] already exist");
        }

        Person person = Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .email(personDto.getEmail())
                .phone(personDto.getPhone())
                .build();
        personRepository.save(person);
    }

    @GetMapping("person/all")
    public List<PersonDto> getAllPersons() {
        List<Person> list = personRepository.findAll();
        return list.stream().map(person -> PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .phone(person.getPhone())
                .build()).collect(Collectors.toList());
    }

    @GetMapping("person/{id}")
    public PersonDto getPersonById(@PathVariable("id") Integer id) {

        return personRepository.findById(id)
                .map(person -> PersonDto.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .email(person.getEmail())
                        .phone(person.getPhone())
                        .build())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id [" + id + "] does not exist"));
    }

    @PostMapping("car")
    public String addCar(@RequestBody CatalogCarDto catalogCarDto) {
        if (catalogCarDto.getId() != null && carCatalogRepository.existsById(new ObjectId(catalogCarDto.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return carCatalogRepository.save(CatalogCar.builder()
                .model(catalogCarDto.getModel())
                .price(catalogCarDto.getPrice().doubleValue())
                .colors(catalogCarDto.getColors())
                .build())
                .getId().toHexString();
    }

    @GetMapping("car/all")
    public List<CatalogCarDto> getAll() {
        return carCatalogRepository.findAll().stream()
                .map(catalogCar -> CatalogCarDto.builder()
                        .id(catalogCar.getId().toHexString())
                        .model(catalogCar.getModel())
                        .colors(catalogCar.getColors())
                        .price(BigDecimal.valueOf(catalogCar.getPrice()))
                        .build())
                .collect(Collectors.toList());
    }
}
