package uk.co.luciditysoftware.campervibe.domain.repositorycontracts;

import java.util.List;

import uk.co.luciditysoftware.campervibe.domain.entities.Vehicle;

public interface VehicleRepository {
	public List<Vehicle> getAll();
}
