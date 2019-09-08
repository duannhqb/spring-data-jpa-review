/**
 * 
 */
package edu.poly.spring.service.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.poly.spring.domain.Authority;
import edu.poly.spring.domain.Person;
import edu.poly.spring.domain.User;
import edu.poly.spring.service.dto.UserDTO;

/**
 * @author Huu Duan
 *
 */
@Component
public class UserMapper implements EntityMapper<User, UserDTO> {

	@Override
	public UserDTO toDto(User user) {
		Optional<User> optional = Optional.of(user);
		UserDTO dto = new UserDTO();

		optional.ifPresent(entity -> {
			dto.setId(entity.getId());

			Set<String> authorities = new HashSet<>();
			entity.getAuthorities().forEach(authority -> authorities.add(authority.toString()));

			dto.setAuthorities(authorities);
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setLogin(entity.getLogin());
			dto.setPassword(entity.getPassword());
			dto.setPersonId(entity.getPerson().getId());
		});

		return Optional.ofNullable(dto).get();
	}

	@Override
	public User toEntity(UserDTO userDTO) {
		Optional<UserDTO> optional = Optional.of(userDTO);
		User user = new User();
		Authority auth = new Authority();

		optional.ifPresent(dto -> {
			Set<Authority> authorities = new HashSet<>();
			dto.getAuthorities().forEach(authority -> {
				auth.setName(authority);
				authorities.add(auth);
			});
			user.setAuthorities(authorities);

			user.setFirstName(dto.getFirstName());
			user.setLastName(dto.getLastName());
			user.setId(dto.getId());
			user.setLogin(dto.getLogin());
			user.setPassword(dto.getPassword());

			Person person = new Person();
			person.setId(user.getPerson().getId());
			user.setPerson(person);
		});

		return Optional.ofNullable(user).get();
	}

}
