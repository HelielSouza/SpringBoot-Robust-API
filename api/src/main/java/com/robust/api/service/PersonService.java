package com.robust.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robust.api.data.dto.PersonDto;
import com.robust.api.data.model.Person;
import com.robust.api.exception.ResourceNotFoundException;
import com.robust.api.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	ModelMapper modelMapper = new ModelMapper();
	
	public List<PersonDto> findAll() {
	    List<Person> persons = repository.findAll(); 
	    return persons.stream()
	                  .map(person -> modelMapper.map(person, PersonDto.class)) 
	                  .collect(Collectors.toList()); 
	}
	
	public PersonDto findById (Long id) {
		Person obj = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		PersonDto objDto = modelMapper.map(obj, PersonDto.class);
		return objDto;
	}
}
