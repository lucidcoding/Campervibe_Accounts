package uk.co.luciditysoftware.campervibe.data.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import uk.co.luciditysoftware.campervibe.domain.entities.Depot;
import uk.co.luciditysoftware.campervibe.domain.entities.Vehicle;
import uk.co.luciditysoftware.campervibe.domain.repositorycontracts.VehicleRepository;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

	@Override
	public List<Vehicle> getAll() {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		Depot depot = new Depot();
		depot.setId(UUID.randomUUID());
		depot.setName("Manchester");
		depot.setCode("MAN01");
		
		Vehicle vehicle1 = new Vehicle(){{
			setId(UUID.fromString("399b2bfb-1aa3-425a-a702-7639c2c33905"));
			setName("Archie");
			setMake("Volkswagen");
			setModel("T1");
			setHomeDepot(depot);
			setPricePerDay(new BigDecimal(10.0));
		}};
		
		Vehicle vehicle2 = new Vehicle(){{
			setId(UUID.fromString("9dd60ebb-e0d7-46c2-bb39-d3ed35a867d2"));
			setName("Barry");
			setMake("Volkswagen");
			setModel("T2");
			setHomeDepot(depot);
			setPricePerDay(new BigDecimal(10.0));
		}};
		
		vehicles.add(vehicle1);
		vehicles.add(vehicle2);
		return vehicles;
	}
}
