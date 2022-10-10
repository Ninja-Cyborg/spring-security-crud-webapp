package com.spring.crmapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.crmapp.entity.Client;
import com.spring.crmapp.service.ClientService;
import com.spring.crmapp.utils.SortUtils;

@Controller
@RequestMapping("client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/list")
	public String listClients(Model model, @RequestParam(required=false) String sort) {
		
		// get clients
		List<Client> clients = null;
		
		if (sort != null) {
			int sortField = Integer.parseInt(sort);
			clients = clientService.getClients(sortField);			
		}
		else {
			clients = clientService.getClients(SortUtils.LAST_NAME);
		}
		
		model.addAttribute("clients", clients);
		
		return "list-clients";
	}
	
	// addClient form  
	@GetMapping("/addClient")
	public String showFormForAdd(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		
		return "client-form";
	}
	
	// save new Client
	@PostMapping("/saveClient")
	public String saveClient(@ModelAttribute("client") Client client) {
		
		clientService.saveClient(client);
		return "redirect:/client/list";
	}
	
	// update Client info form
	@GetMapping("/updateClient")
	public String showFormForUpdate(@RequestParam("clientId") int id,
																Model model) {
		// get client
		Client client = clientService.getClient(id);
		model.addAttribute("client", client);
		
		return "client-form";
	}
	
	// delete client
	public String deleteClient(@RequestParam("clientId") int id) {
		
		clientService.deleteClient(id);
		
		return "redirect:/client/list";
	}
	
	// search client by field
	 @GetMapping("/search")
	    public String findClient(@RequestParam("clientName") String clientName,
	                                    Model theModel) {
	        // search client
	        List<Client> clients = clientService.findClient(clientName);
	                
	        // add client to model
	        theModel.addAttribute("clients", clients);
	        return "list-clients";        
	    }
	
}
