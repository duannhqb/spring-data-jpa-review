package edu.poly.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.service.dto.PersonDTO;

public interface PersonService {

	PersonDTO getOne(Long id);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<PersonDTO> findById(Long id);

	List<PersonDTO> findAll(Sort sort);

	List<PersonDTO> findAll();

	Page<PersonDTO> findAll(Pageable pageable);

	Optional<PersonDTO> save(PersonDTO personDTO);
}
