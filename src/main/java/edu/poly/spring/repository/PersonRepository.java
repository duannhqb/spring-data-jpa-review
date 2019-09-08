package edu.poly.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.spring.domain.Person;

@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
