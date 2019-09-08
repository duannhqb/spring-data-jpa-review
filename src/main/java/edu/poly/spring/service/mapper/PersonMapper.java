package edu.poly.spring.service.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.poly.spring.domain.Person;
import edu.poly.spring.domain.User;
import edu.poly.spring.service.dto.PersonDTO;

@Component
public class PersonMapper implements EntityMapper<Person, PersonDTO> {

	@Override
	public Person toEntity(PersonDTO dto) {
		Optional<PersonDTO> optional = Optional.of(dto);
		Person person = new Person();

		optional.ifPresent(personDTO -> {
			person.setId(personDTO.getId());
			person.setAddress(personDTO.getAddress());
			person.setBirthday(personDTO.getBirthday());

			User user = new User();
			user.setId(personDTO.getUserId());

			person.setUser(user);
		});

		return Optional.ofNullable(person).get();
	}

	@Override
	public PersonDTO toDto(Person entity) {
		Optional<Person> optional = Optional.of(entity);
		PersonDTO personDTO = new PersonDTO();

		optional.ifPresent(person -> {
			personDTO.setId(person.getId());
			personDTO.setAddress(person.getAddress());
			personDTO.setBirthday(person.getBirthday());
			personDTO.setUserId(person.getUser().getId());
			personDTO.setFirstName(person.getUser().getFirstName());
			personDTO.setLastName(person.getUser().getLastName());
			personDTO.setLogin(person.getUser().getLogin());
			personDTO.setPassword(person.getUser().getPassword());

			Set<String> authorities = new HashSet<>();
			person.getUser().getAuthorities().forEach((authority) -> authorities.add(authority.getName()));

			personDTO.setAuthorities(authorities);
		});

		return Optional.ofNullable(personDTO).get();
	}
}
