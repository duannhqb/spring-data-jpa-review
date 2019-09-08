package edu.poly.spring.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.spring.service.PersonService;
import edu.poly.spring.service.dto.PersonDTO;

@RestController
@RequestMapping("/api")
public class PersonResources {

	private final PersonService personService;

	public PersonResources(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/persons")
	public ResponseEntity<List<PersonDTO>> getPersons(Pageable pageable) {
		Page<PersonDTO> page = personService.findAll(pageable);
		return ResponseEntity.ok(page.getContent());
	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
		return Optional.ofNullable(personService.findById(id).get()).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/persons")
	public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) throws URISyntaxException {
		if (personDTO.getId() != null) {
			return ResponseEntity.badRequest().body(null);
		}
		Optional<PersonDTO> person = personService.save(personDTO);

		return ResponseEntity.created(new URI("/api/persons/" + person.get().getId())).body(person.get());
	}

	@PutMapping("/persons")
	public ResponseEntity<PersonDTO> updatePerson(@Valid @RequestBody PersonDTO personDTO) {
		if (personDTO.getId() == null) {
			return ResponseEntity.badRequest().body(null);
		}

		return Optional.ofNullable(personService.save(personDTO).get()).map(ResponseEntity::ok)
				.orElse(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/persons/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
		Optional<PersonDTO> optional = personService.findById(id);
		if (optional.isPresent()) {
			personService.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

}
