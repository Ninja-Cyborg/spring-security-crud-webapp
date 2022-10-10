package com.spring.crmapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.crmapp.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserDetailsManager userDetailsManager;
	
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	// registration form
	@GetMapping("/showRegistrationForm")
	public String showLoginPage(Model model) {
		model.addAttribute("crmUser", new CrmUser());
		
		return "registration-form";
	}
	
	// process form
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
							@Valid @ModelAttribute("crmUser") CrmUser crmUser,
							BindingResult bindingResult,
							Model model) {
		String username = crmUser.getUsername();
		
		// form/form-input validations
		if(bindingResult.hasErrors()) {
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User name/password can not be empty");
			
			return "registration-form";
		}
		
		// check database if user already exist
		boolean userExist = doesUserExist(username);
		
		if(userExist) {
			model.addAttribute("crmUser", new CrmUser());
			model.addAttribute("registrationError", "User already Exist");
			
			return "registration-form";
		}
		
		// encrypt password
		String encodedPassword = passwordEncoder.encode(crmUser.getPassword());
		encodedPassword = "{bcrypt}" + encodedPassword;
		
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("Role_Employee");
		User tempUser = new User(username, encodedPassword, authorities);
		
		// save user in db
		userDetailsManager.createUser(tempUser);
		
		return "registration-confirmation";
		}

	// user validation | helper method
	private boolean doesUserExist(String username) {
		boolean exists = userDetailsManager.userExists(username);
		
		return exists;
	}
	
}
