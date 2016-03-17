package uk.co.luciditysoftware.campervibe.data.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import uk.co.luciditysoftware.campervibe.data.common.SessionFactoryFactory;
import uk.co.luciditysoftware.campervibe.domain.entities.Depot;
import uk.co.luciditysoftware.campervibe.domain.entities.Vehicle;
import uk.co.luciditysoftware.campervibe.domain.repositorycontracts.VehicleRepository;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

	private SessionFactoryFactory sessionFactoryFactory;
	
	@Inject
	public void setSessionFactoryFactory(SessionFactoryFactory sessionFactoryFactory) {
		this.sessionFactoryFactory = sessionFactoryFactory;
	}
	
	@Override
	public List<Vehicle> getAll() {

		Session session = null;
		Transaction transaction = null;
		List<Vehicle> vehicles = null;
		
		
		//Query query = session.createQuery("from Vehicle"); 
		//List<Vehicle> list = query.list(); 
		
		try {
			
			session = sessionFactoryFactory.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			vehicles = session.createCriteria(Vehicle.class).list();
			Depot depot = vehicles.get(0).getHomeDepot();
			transaction.commit();
			
		} catch(Exception ex) {
			
			transaction.rollback();
			throw ex;
			
		} finally {
			
			session.close();
		}
		
		return vehicles;
	}
}
