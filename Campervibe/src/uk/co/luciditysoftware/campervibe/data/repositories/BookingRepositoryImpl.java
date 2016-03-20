package uk.co.luciditysoftware.campervibe.data.repositories;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import uk.co.luciditysoftware.campervibe.config.Bootstrap;
import uk.co.luciditysoftware.campervibe.domain.entities.Booking;
import uk.co.luciditysoftware.campervibe.domain.repositorycontracts.BookingRepository;

@Repository
@Scope("prototype")
public class BookingRepositoryImpl implements BookingRepository {

	@Override
	public Booking getById(UUID id) {
		Session session = Bootstrap.sessionFactory.getCurrentSession();
		Booking booking = (Booking)session.get(Booking.class, id);
		return booking;
	}
	
	@Override
	public List<Booking> getAll() {
		Session session = Bootstrap.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Booking> bookings = session.createCriteria(Booking.class).list();
		return bookings;
	}

	@Override
	public void save(Booking booking) {
		Session session = Bootstrap.sessionFactory.getCurrentSession();
		session.save(booking);
	}
}
