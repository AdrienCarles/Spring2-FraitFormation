package fr.projet.declarationfrais.controllers;

import fr.projet.declarationfrais.model.Utilisateur;
import fr.projet.declarationfrais.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private UtilisateurRepository utilisateurRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "flows/index";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new Utilisateur());
		
		return "register";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Utilisateur utilisateur) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(utilisateur.getPassword());
		utilisateur.setPassword(encodedPassword);
		logger.info(utilisateur.toString());
		utilisateur.setTypeUtilisateur(0);
		utilisateurRepo.save(utilisateur);
		
		return "register_success";
	}

}