package com.trycore.person.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trycore.commons.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public Optional<Person> findByIdentificationNumber(int identificationNumber);

}
