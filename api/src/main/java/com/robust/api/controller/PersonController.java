package com.robust.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robust.api.data.dto.PersonDto;
import com.robust.api.service.PersonService;


@RestController
@RequestMapping(value = "/person")
public class PersonController {

	private final String ID = "id";
	
	@Autowired
	private PersonService service;
	
	@GetMapping()
	public ResponseEntity<List<PersonDto>> findAll() {
		List<PersonDto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonDto> findById(@PathVariable(ID) Long id) {
		PersonDto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
