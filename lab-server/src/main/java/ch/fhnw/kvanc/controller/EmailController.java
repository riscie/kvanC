package ch.fhnw.kvanc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.kvanc.model.Email;
import ch.fhnw.kvanc.persistence.EmailRepository;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/emails")
public class EmailController {
	@Autowired
	private EmailRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Email>> list(Model model) {
		List<Email> emails = repo.findAll();

		return new ResponseEntity<List<Email>>(emails, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Email> create(@Valid @RequestBody Email e) {
		Email email = repo.save(e);
		return new ResponseEntity<Email>(email, HttpStatus.CREATED);
	}
}
