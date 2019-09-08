/**
 * 
 */
package edu.poly.spring.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.poly.spring.domain.Authority;
import edu.poly.spring.domain.User;
import edu.poly.spring.repository.AuthorityRepository;
import edu.poly.spring.repository.UserRepository;

/**
 * @author Huu Duan
 *
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		// Authority
		if (!authorityRepository.existsById("ROLE_ADMIN")) {
			authorityRepository.save(new Authority("ROLE_ADMIN"));
		}

		if (!authorityRepository.existsById("ROLE_USER")) {
			authorityRepository.save(new Authority("ROLE_USER"));
		}

		// Admin account
		if (userRepository.findByLogin("admin").getId() == null) {
			User admin = new User();
			admin.setLogin("admin");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setFirstName("Admin");
			admin.setLastName("Admin");
			admin.setPerson(null);

			Set<Authority> authorities = new HashSet<>();
			authorities.add(authorityRepository.findByName("ROLE_ADMIN"));
			authorities.add(authorityRepository.findByName("ROLE_USER"));
			admin.setAuthorities(authorities);

			userRepository.save(admin);
		}

		// User account
		if (userRepository.findByLogin("user") == null) {
			User user = new User();
			user.setLogin("user");
			user.setPassword(passwordEncoder.encode("user"));
			user.setFirstName("User");
			user.setLastName("User");
			user.setPerson(null);

			Set<Authority> authorities = new HashSet<>();
			authorities.add(authorityRepository.findByName("ROLE_USER"));
			user.setAuthorities(authorities);

			userRepository.save(user);
		}
	}

}