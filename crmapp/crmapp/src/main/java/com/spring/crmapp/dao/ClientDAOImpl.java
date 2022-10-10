package com.spring.crmapp.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.crmapp.entity.Client;
import com.spring.crmapp.utils.SortUtils;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Client> getClients(int sortField) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		String fieldName = null;
		
		switch (sortField) {
			case SortUtils.FIRST_NAME: 
				fieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				fieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				fieldName = "email";
				break;
			default:
				// if nothing matches the default to sort by lastName
				fieldName = "lastName";
		}
		
		// create a query for sort
		String queryString = "from Client order by " + fieldName;
		 
		Query<Client> query = currentSession.createQuery(queryString, Client.class);
		List<Client> clients = query.getResultList();
		
		return clients;
	}

	@Override
	public void saveClient(Client client) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(client);
	}

	@Override
	public Client getClient(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Client client = currentSession.get(Client.class, id);
		
		return client;
	}

	@Override
	public void deleteClient(int id) {
			Session currentSession = sessionFactory.getCurrentSession();
			Query query = currentSession.createQuery("delete from Client where id=:clientId");
			query.setParameter("clientId", id);
			query.executeUpdate();
	}

	@Override
	public List<Client> searchClient(String clientName) {
		 Session currentSession = sessionFactory.getCurrentSession();
	        
	        Query<Client> theQuery = null;
	        if (clientName != null && clientName.trim().length() > 0) {
	            theQuery =currentSession.createQuery("from Client where lower(firstName) like :name or lower(lastName) like :name", Client.class);
	            theQuery.setParameter("name", "%" + clientName.toLowerCase() + "%");
	        }
	        else {
	            // return whole list | when search field empty
	            theQuery =currentSession.createQuery("from Client", Client.class);            
	        }
	        
	        List<Client> clients = theQuery.getResultList();
	                 
	        return clients;
	}

}
