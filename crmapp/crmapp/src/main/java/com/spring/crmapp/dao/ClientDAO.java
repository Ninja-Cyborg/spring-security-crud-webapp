package com.spring.crmapp.dao;

import java.util.List;

import com.spring.crmapp.entity.Client;

public interface ClientDAO {

	public List<Client> getClients(int sortField);
	
	public void saveClient(Client client);
	
	public Client getClient(int id);
	
	public void deleteClient(int id);
	
	public List<Client> searchClient(String clientName);

}
