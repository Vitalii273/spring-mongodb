package com.telran.springmongodb.data;

import com.telran.springmongodb.data.document.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person,Integer> {
}
