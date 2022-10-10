package com.spring.crmapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
		// login page mapping
		@GetMapping("/showLoginPage")
		public String showLoginPage() {
			return "loginPage";
		}
		
		// failed access mapping
		@GetMapping("/access-denied")
		public String showAccessDenied() {
			return "access-denied";
		}
		
}
