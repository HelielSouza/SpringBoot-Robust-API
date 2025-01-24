package com.robust.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.robust.api.controller.PersonController;
import com.robust.api.data.dto.PersonResponseDto;
import com.robust.api.data.dto.PersonRequestDto;
import com.robust.api.data.model.Person;
import com.robust.api.exception.RequiredObjectIsNullException;
import com.robust.api.exception.ResourceNotFoundException;
import com.robust.api.mapper.ObjectMapper;
import com.robust.api.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public List<PersonResponseDto> findAll() { 
	 	List<Person> list = repository.findAll();
	 	
	 	List<PersonResponseDto>dtoList = ObjectMapper.convertList(list, PersonResponseDto.class);
	 	dtoList
	 		.stream()
	 		.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
	 	return dtoList;
	 }
	 
	public PersonResponseDto findById(Long id) {
		Person obj = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		PersonResponseDto objDto = ObjectMapper.convertObject(obj, PersonResponseDto.class);
		objDto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return objDto;
	}
	
	public PersonResponseDto insert(PersonRequestDto obj) {
		if(obj == null) throw new RequiredObjectIsNullException();
		
		Person entity = ObjectMapper.convertObject(obj, Person.class);
		PersonResponseDto objInserted = ObjectMapper.convertObject(repository.save(entity), PersonResponseDto.class);
		objInserted.add(linkTo(methodOn(PersonController.class).findById(objInserted.getKey())).withSelfRel());
		return objInserted;
	}
	
	public void delete(Long id) {
		Person obj = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		repository.delete(obj);
	}
	
	public PersonResponseDto update(PersonRequestDto obj, Long id) {
		if(obj == null) throw new RequiredObjectIsNullException();
		
		Person newEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		updateData(newEntity, obj);
		PersonResponseDto objConverted = ObjectMapper.convertObject(repository.save(newEntity), PersonResponseDto.class);
		objConverted.add(linkTo(methodOn(PersonController.class).findById(objConverted.getKey())).withSelfRel());
		return objConverted;
	}
	
	private void updateData(Person newObj, PersonRequestDto obj) {
		newObj.setFirstName(obj.getFirstName());
		newObj.setLastName(obj.getLastName());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
		newObj.setGender(obj.getGender());
		newObj.setAddress(obj.getAddress());
	}
}