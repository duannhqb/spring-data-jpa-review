/**
 * 
 */
package edu.poly.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.spring.domain.Authority;

/**
 * @author Huu Duan
 *
 */
@Repository
@Transactional
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
