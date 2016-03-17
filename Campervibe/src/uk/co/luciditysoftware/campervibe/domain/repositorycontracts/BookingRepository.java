package uk.co.luciditysoftware.campervibe.domain.repositorycontracts;

import java.util.List;

import uk.co.luciditysoftware.campervibe.domain.entities.Booking;

public interface BookingRepository {
	public List<Booking> getAll();
	public void save(Booking booking);
}
