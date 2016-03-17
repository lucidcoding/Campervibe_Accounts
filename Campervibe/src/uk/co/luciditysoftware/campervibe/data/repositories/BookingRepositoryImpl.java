package uk.co.luciditysoftware.campervibe.data.repositories;

import java.util.ArrayList;
import java.util.Date;
//import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import uk.co.luciditysoftware.campervibe.data.common.SessionFactoryFactory;
import uk.co.luciditysoftware.campervibe.domain.entities.Booking;
import uk.co.luciditysoftware.campervibe.domain.entities.Depot;
import uk.co.luciditysoftware.campervibe.domain.entities.Vehicle;
import uk.co.luciditysoftware.campervibe.domain.repositorycontracts.BookingRepository;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

	private SessionFactoryFactory sessionFactoryFactory;
	
	@Inject
	public void setSessionFactoryFactory(SessionFactoryFactory sessionFactoryFactory) {
		this.sessionFactoryFactory = sessionFactoryFactory;
	}
	
	@Override
	public List<Booking> getAll() {
		
		Session session = sessionFactoryFactory.getSessionFactory().openSession();
		
		//Query query = session.createQuery("from Vehicle"); 
		//List<Vehicle> list = query.list(); 
		
		List<Booking> bookings = session.createCriteria(Booking.class).list();
		
		for (Booking booking : bookings) {
			String a = booking.getVehicle().getName();
		}

		session.close();
		return bookings;
		
		/*ArrayList<Booking> bookings = new ArrayList<Booking>();
		Booking booking1 = new Booking();
	    booking1.setStartDate(new Date(2015, 10, 01));
	    booking1.setEndDate(new Date(2015, 10, 05));
	    booking1.setBookingNumber("A00001");
	    Vehicle vehicle1 = new Vehicle();
	    vehicle1.setName("Archie");
	    booking1.setVehicle(vehicle1);
	    Booking booking2 = new Booking();
	    booking2.setStartDate(new Date(2015, 10, 01));
	    booking2.setEndDate(new Date(2015, 10, 05));
	    booking2.setBookingNumber("A00002");
	    Vehicle vehicle2 = new Vehicle();
	    vehicle2.setName("Betty");
	    booking2.setVehicle(vehicle2);
	    bookings.add(booking1);
	    bookings.add(booking2);
	    return bookings;*/
	}

	@Override
	public void save(Booking booking) {

		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactoryFactory.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(booking);
			transaction.commit();
		} catch(Exception ex) {
			transaction.rollback();
			throw ex;
		} finally {
			session.close();
		}
	}
}
