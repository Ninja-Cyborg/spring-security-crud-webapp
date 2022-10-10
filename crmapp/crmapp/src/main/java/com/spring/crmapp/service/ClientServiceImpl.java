package com.spring.crmapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.crmapp.dao.ClientDAO;
import com.spring.crmapp.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientDAO;
	
	@Override
	@Transactional
	public List<Client> getClients(int sortField) {
		return clientDAO.getClients(sortField);
	}

	@Override
	@Transactional
	public void saveClient(Client client) {
		clientDAO.saveClient(client);
	}

	@Override
	@Transactional
	public Client getClient(int id) {
		return clientDAO.getClient(id);
	}

	@Override
	@Transactional
	public void deleteClient(int id) {
		clientDAO.deleteClient(id);
	}
	
	@Override
    @Transactional
    public List<Client> findClient(String clientName) {
        return clientDAO.searchClient(clientName);
    }

}
