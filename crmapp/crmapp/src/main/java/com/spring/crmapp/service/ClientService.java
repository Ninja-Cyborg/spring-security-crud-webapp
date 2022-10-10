package com.spring.crmapp.service;

import java.util.List;

import com.spring.crmapp.entity.Client;

public interface ClientService {
	
	public List<Client> getClients(int sortField);
	
	public void saveClient(Client client);
	
	public Client getClient(int id);
	
	public void deleteClient(int id);

	public List<Client> findClient(String clientName);

}
