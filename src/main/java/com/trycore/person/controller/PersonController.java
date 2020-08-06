package com.trycore.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trycore.commons.dto.PersonDTO;
import com.trycore.commons.dto.PersonResponseDTO;
import com.trycore.person.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	PersonService personService;

	@PostMapping("person")
	public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody PersonDTO personDTO) {
		return new ResponseEntity<>(personService.createPerson(personDTO), HttpStatus.CREATED);
	}

	@GetMapping("person/{identificationNumber}")
	public ResponseEntity<PersonResponseDTO> getPerson(@PathVariable int identificationNumber) {
		return new ResponseEntity<>(personService.getPerson(identificationNumber), HttpStatus.CREATED);
	}

	@PatchMapping("person")
	public ResponseEntity<PersonResponseDTO> updatePerson(@RequestBody PersonDTO personDTO) {
		return new ResponseEntity<>(personService.updatePerson(personDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("person")
	public ResponseEntity<PersonResponseDTO> disablePerson(int identificationNumber, Long idPlanet) {
		return new ResponseEntity<>(personService.disablePerson(identificationNumber, idPlanet), HttpStatus.CREATED);
	}
	
	
	@PostMapping("person")
	public ResponseEntity<PersonResponseDTO> counterViews(int identificationNumber) {
		return new ResponseEntity<>(personService.counterViews(identificationNumber), HttpStatus.CREATED);
	}
}
