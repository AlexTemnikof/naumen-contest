package com.example.contestApp.mapper;

import com.example.contestApp.dto.PersonDTO;
import com.example.contestApp.entities.Person;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PersonMapper {
    PersonDTO toDTO(Person person);

    List<PersonDTO> toDTOAll(List<Person> persons);

    Person toEntity(PersonDTO personDTO);


}
