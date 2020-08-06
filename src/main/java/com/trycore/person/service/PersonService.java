package com.trycore.person.service;

import com.trycore.commons.dto.PersonDTO;
import com.trycore.commons.dto.PersonResponseDTO;

public interface PersonService {

	public PersonResponseDTO createPerson(PersonDTO personDTO);

	public PersonResponseDTO getPerson(int identificationNumber);

	public PersonResponseDTO updatePerson(PersonDTO personDTO);

	public PersonResponseDTO disablePerson(int identificationNumber, Long idPlanet);

	public PersonResponseDTO counterViews(int identificationNumber);
}
