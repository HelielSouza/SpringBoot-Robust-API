package com.robust.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 	return dtoList;
	 }
	 
	public PersonDto findById (Long id) {
		Person obj = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		PersonDto objDto = ObjectMapper.convertObject(obj, PersonDto.class);
		return objDto;
	}
	
	public PersonDto insert (PersonInsertDto obj) {
		if(obj == null) throw new RequiredObjectIsNullException();
		
		Person entity = ObjectMapper.convertObject(obj, Person.class);
		PersonDto objInserted = ObjectMapper.convertObject(repository.save(entity), PersonDto.class);
		
		return objInserted;
	}
}