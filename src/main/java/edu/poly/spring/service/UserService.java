/**
 * 
 */
package edu.poly.spring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.service.dto.UserDTO;

/**
 * @author Huu Duan
 *
 */
public interface UserService {

	void delete(UserDTO userDTO);

	UserDTO getOne(Long id);

	void deleteById(Long id);

	boolean existsById(Long id);

	Optional<UserDTO> findById(Long id);

	Page<UserDTO> findAll(Sort sort);

	Page<UserDTO> findAll();

	Page<UserDTO> findAll(Pageable pageable);

	Optional<UserDTO> save(UserDTO userDTO);

}
