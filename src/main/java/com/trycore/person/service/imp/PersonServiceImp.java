package com.trycore.person.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trycore.commons.dto.PersonDTO;
import com.trycore.commons.dto.PersonResponseDTO;
import com.trycore.commons.dto.PlanetResponseDTO;
import com.trycore.commons.exception.CustomException;
import com.trycore.commons.messages.MessagesEnum;
import com.trycore.commons.model.Person;
import com.trycore.commons.model.Planet;
import com.trycore.person.repository.PersonRepository;
import com.trycore.person.service.PersonService;
import com.trycore.person.service.PlanetService;

@Service
public class PersonServiceImp implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PlanetService planetService;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PersonResponseDTO createPerson(PersonDTO personDTO) {
		Person person = new Person();
		Planet planet = new Planet();
		PlanetResponseDTO planetResponseDTO = planetService.getPlanet(personDTO.getIdPlanet()).getBody();
		if (planetResponseDTO.getPlanetsDTO().isEmpty()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, MessagesEnum.PLANET_NOT_FOUND,
					Long.toString(personDTO.getIdPlanet()));
		}

		modelMapper.map(personDTO, person);
		planet = modelMapper.map(planetResponseDTO.getPlanetsDTO().get(0), Planet.class);
		person.setPlanet(planet);
		personRepository.save(person);
		return new PersonResponseDTO(MessagesEnum.SUCCESFULLY_OPERATION.code,
				MessagesEnum.SUCCESFULLY_OPERATION.description);
	}

	@Override
	public PersonResponseDTO getPerson(int identificationNumber) {
		PersonDTO personDTO = new PersonDTO();
		List<PersonDTO> personsDTO = new ArrayList<>();
		Optional<Person> checkPerson = personRepository.findByIdentificationNumber(identificationNumber);
		if (!checkPerson.isPresent()) {
			throw new CustomException(HttpStatus.NOT_FOUND, MessagesEnum.PERSON_NOT_FOUND,
					Long.toString(identificationNumber));
		}
		modelMapper.map(checkPerson, personDTO);
		return new PersonResponseDTO(MessagesEnum.SUCCESFULLY_OPERATION.code,
				MessagesEnum.SUCCESFULLY_OPERATION.description, personsDTO);
	}

	@Override
	public PersonResponseDTO updatePerson(PersonDTO personDTO) {
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		List<PersonDTO> personsDTO = new ArrayList<>();
		Optional<Person> checkPerson = personRepository.findByIdentificationNumber(personDTO.getIdentificationNumber());
		if (!checkPerson.isPresent()) {
			throw new CustomException(HttpStatus.NOT_FOUND, MessagesEnum.PERSON_NOT_FOUND,
					Long.toString(personDTO.getIdentificationNumber()));
		}
		modelMapper.map(checkPerson, personDTO);
		return new PersonResponseDTO(MessagesEnum.SUCCESFULLY_OPERATION.code,
				MessagesEnum.SUCCESFULLY_OPERATION.description, personsDTO);

	}

	@Override
	public PersonResponseDTO disablePerson(int identificationNumber, Long idPlanet) {
		Optional<Person> checkPerson = personRepository.findByIdentificationNumber(identificationNumber);
		if (!checkPerson.isPresent()) {
			throw new CustomException(HttpStatus.NOT_FOUND, MessagesEnum.PERSON_NOT_FOUND,
					Long.toString(identificationNumber));
		}
		checkPerson.get().setEnabled(false);
		personRepository.save(checkPerson.get());
		return new PersonResponseDTO(MessagesEnum.SUCCESFULLY_OPERATION.code,
				MessagesEnum.SUCCESFULLY_OPERATION.description);
	}

	@Override
	public PersonResponseDTO counterViews(int identificationNumber) {
		Optional<Person> checkPerson = personRepository.findByIdentificationNumber(identificationNumber);
		if (!checkPerson.isPresent()) {
			throw new CustomException(HttpStatus.NOT_FOUND, MessagesEnum.PERSON_NOT_FOUND,
					Long.toString(identificationNumber));
		}
		checkPerson.get().setCounterViews(checkPerson.get().getCounterViews() + 1);
		return new PersonResponseDTO(MessagesEnum.SUCCESFULLY_OPERATION.code,
				MessagesEnum.SUCCESFULLY_OPERATION.description);
	}

}
