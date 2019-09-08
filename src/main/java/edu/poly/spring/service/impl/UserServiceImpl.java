/**
 * 
 */
package edu.poly.spring.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.domain.User;
import edu.poly.spring.repository.UserRepository;
import edu.poly.spring.service.UserService;
import edu.poly.spring.service.dto.UserDTO;
import edu.poly.spring.service.mapper.UserMapper;

/**
 * @author Huu Duan
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public void delete(UserDTO userDTO) {
		Optional<User> optional = Optional
				.of(userRepository.getOne(userDTO.getId()));
		optional.ifPresent(entity -> userRepository.delete(entity));
	}

	@Override
	public UserDTO getOne(Long id) {
		return Optional
				.ofNullable(userRepository.getOne(id))
				.map(userMapper::toDto)
				.orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return Optional
				.ofNullable(userRepository.existsById(id))
				.isPresent();
	}

	@Override
	public Optional<UserDTO> findById(Long id) {
		UserDTO dto = Optional
				.ofNullable(userRepository.findById(id).get())
				.map(userMapper::toDto)
				.orElse(null);

		return Optional.of(dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<UserDTO> findAll(Sort sort) {
		List<UserDTO> userDTOs = userRepository.findAll(sort)
				.stream()
				.map(userMapper::toDto)
				.collect(Collectors.toList()); 

		return (Page<UserDTO>) userDTOs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<UserDTO> findAll() {
		return (Page<UserDTO>) userRepository.findAll()
				.stream()
				.map(userMapper::toDto)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<UserDTO> findAll(Pageable pageable) {
		return (Page<UserDTO>) userRepository.findAll(pageable)
				.stream()
				.map(userMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<UserDTO> save(UserDTO userDTO) {
		User user = Optional
				.ofNullable(userDTO)
				.map(userMapper::toEntity)
				.get();
		
		return Optional
				.ofNullable(userRepository.save(user))
				.map(userMapper::toDto);
	}

}
