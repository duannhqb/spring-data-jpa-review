package edu.poly.spring.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.domain.Person;
import edu.poly.spring.repository.PersonRepository;
import edu.poly.spring.service.PersonService;
import edu.poly.spring.service.UserService;
import edu.poly.spring.service.dto.PersonDTO;
import edu.poly.spring.service.dto.UserDTO;
import edu.poly.spring.service.mapper.PersonMapper;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PersonMapper personMapper;

	@Override
	public PersonDTO getOne(Long id) {
		return Optional
				.ofNullable(personRepository.getOne(id))
				.map(personMapper::toDto)
				.orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		personRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return personRepository.existsById(id);
	}

	@Override
	public Optional<PersonDTO> findById(Long id) {
		return Optional
				.ofNullable(personRepository.findById(id)
				.map(personMapper::toDto)
				.orElse(null));
	}

	@Override
	public List<PersonDTO> findAll(Sort sort) {
		return personRepository.findAll(sort)
				.stream().map(personMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<PersonDTO> findAll() {
		return personRepository.findAll()
				.stream()
				.map(personMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Page<PersonDTO> findAll(Pageable pageable) {
		return personRepository.findAll(pageable)
				.map(personMapper::toDto);
	}

	@Override
	public Optional<PersonDTO> save(PersonDTO personDTO) {
		Set<String> authorities = new HashSet<>();
		authorities.add("ROLE_USER");
		personDTO.setAuthorities(authorities);

		UserDTO user = userService.save(personDTO).get();
		personDTO.setUserId(user.getId()); 
		
		Person person = Optional.ofNullable(personDTO)
				.map(personMapper::toEntity)
				.orElse(null);

		return Optional
				.ofNullable(personRepository.save(person))
				.map(personMapper::toDto);
	}

}
