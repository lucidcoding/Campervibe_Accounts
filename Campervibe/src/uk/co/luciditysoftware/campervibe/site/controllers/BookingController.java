package uk.co.luciditysoftware.campervibe.site.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import uk.co.luciditysoftware.campervibe.site.domain.entities.Booking;
import uk.co.luciditysoftware.campervibe.site.domain.repositorycontracts.BookingRepository;
import uk.co.luciditysoftware.campervibe.site.viewmodels.booking.IndexViewModel;
import uk.co.luciditysoftware.campervibe.site.viewmodels.booking.IndexViewModelRow;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookingController {

	
    private BookingRepository bookingRepository;

    //http://www.baeldung.com/spring-nosuchbeandefinitionexception
    
	@Inject
	public void setBookingRepository(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
    
	@ResponseBody
    @RequestMapping(value = "/booking/index", method = RequestMethod.GET)
    public ModelAndView index(Map<String, Object> model)
    {
		List<Booking> bookings = bookingRepository.getAll();
	    IndexViewModel viewModel = new IndexViewModel();
	    
	    viewModel.setBookings(bookings
	    		.stream()
				.map(booking -> new IndexViewModelRow(
						booking.getId(),
						booking.getBookingNumber(),
						booking.getStartDate(),
						booking.getEndDate(),
						booking.getVehicle().getName()))
				.collect(Collectors.toList()));
		
        model.put("viewModel", viewModel);
        return new ModelAndView("booking/index");
    }
}
