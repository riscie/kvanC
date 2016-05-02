package ch.fhnw.kvanc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fhnw.kvanc.model.Email;
import ch.fhnw.kvanc.persistence.EmailRepository;

@Controller
public class EmailController {
	@Autowired
	private EmailRepository repo;

	@RequestMapping(value = "/emails", params = "form")
	public String create(Model model) {
		model.addAttribute("email", new Email());

		return "emails/create";
	}

	@RequestMapping("/emails")
	public String list(Model model) {
		List<Email> emails = repo.findAll();
		model.addAttribute("emails", emails);

		return "emails/list";
	}

	@RequestMapping(value = "/emails", method = RequestMethod.POST)
	public String create(Email email, Model model) {
		repo.save(email);
		return this.list(model);
	}

	@RequestMapping(value = "/emails/{id}", method = RequestMethod.GET)
	public String show(@PathVariable(value = "id") long id, Model model) {
		Email email = repo.findOne(id);
		List<Email> emails = new ArrayList<Email>();
		emails.add(email);
		model.addAttribute("emails", emails);
		return "emails/list";
	}

	@RequestMapping(value = "/emails/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") long id, Model model) {
		Email email = repo.findOne(id);
		if (email != null) {
			repo.delete(email);
		}
		return this.list(model);
	}

}
