package uk.co.luciditysoftware.campervibe.site.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.co.luciditysoftware.campervibe.domain.entities.Booking;
import uk.co.luciditysoftware.campervibe.domain.repositorycontracts.BookingRepository;
import uk.co.luciditysoftware.campervibe.site.viewmodels.booking.IndexViewModel;
import uk.co.luciditysoftware.campervibe.site.viewmodels.booking.IndexViewModelRow;

//http://stackoverflow.com/questions/7462202/spring-json-request-getting-406-not-acceptable

@RestController
public class BookingRestController {
	private BookingRepository bookingRepository; 
	
	@Inject
	public void setBookingRepository(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	@RequestMapping(value = "/api/booking", method = RequestMethod.GET)
	@Transactional
    public IndexViewModel Index() {
		Collection<Booking> bookings = bookingRepository.getAll();
		IndexViewModel viewModel = new IndexViewModel();

		viewModel.setBookings(
				bookings.stream().map(booking -> new IndexViewModelRow(booking)).collect(Collectors.toList()));
		
        return viewModel;
    }
}
