package ch.fhnw.kvanc.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.kvanc.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
