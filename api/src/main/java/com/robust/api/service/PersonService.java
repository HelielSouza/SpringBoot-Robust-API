package com.robust.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.robust.api.controller.PersonController;
import com.robust.api.data.dto.PersonDto;
import com.robust.api.data.dto.PersonInsertDto;
import com.robust.api.data.model.Person;
import com.robust.api.exception.RequiredObjectIsNullException;
import com.robust.api.exception.ResourceNotFoundException;
import com.robust.api.mapper.ObjectMapper;
import com.robust.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public List<PersonDto> findAll() { 
	 	List<Person> list = repository.findAll();
	 	List<PersonDto>dtoList = ObjectMapper.convertList(list, PersonDto.class);
	 	dtoList
	 		.stream()
	 		.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
	 	return dtoList;
	 }
	 
	public PersonDto findById (Long id) {
		Person obj = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		PersonDto objDto = ObjectMapper.convertObject(obj, PersonDto.class);
		objDto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return objDto;
	}
	
	public PersonDto insert (PersonInsertDto obj) {
		if(obj == null) throw new RequiredObjectIsNullException();
		
		Person entity = ObjectMapper.convertObject(obj, Person.class);
		PersonDto objInserted = ObjectMapper.convertObject(repository.save(entity), PersonDto.class);
		objInserted.add(linkTo(methodOn(PersonController.class).findById(objInserted.getKey())).withSelfRel());
		return objInserted;
	}
}