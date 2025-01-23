package com.robust.api.config;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.robust.api.data.model.Person;
import com.robust.api.repository.PersonRepository;

@Configuration
@Profile("test")
public class H2TestConfig implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;
	 
	@Override
	public void run(String... args) throws Exception {
		Person u1 = new Person(null, "Maria", "Bowen", "maria@gmail.com", "79379237423", "Rua raimundo", "Feminino");
		Person u2 = new Person(null, "Carlos", "Alberto", "carlos@gmail.com", "231532", "Rua Peixoto", "Masculino");
		Person u3 = new Person(null, "Pedro", "Maia", "pedro@gmail.com", "4832048293", "Rua França", "Masculino");
		Person u4 = new Person(null, "Alberto", "Oliveira", "alberto@gmail.com", "3921038", "Rua Agostinho", "Feminino");
		
		personRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
	}
	
}
