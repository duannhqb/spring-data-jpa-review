/**
 * 
 */
package edu.poly.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.spring.domain.User;

/**
 * @author Huu Duan
 *
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

}
