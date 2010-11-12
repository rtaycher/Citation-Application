package citation.data;

public class CitationRecord {

	private Location location;
	private Person person;
	private Vehicle vehicle;
	
	public CitationRecord()
	{
		location = new Location();
		person = new Person();
		vehicle = new Vehicle();
	}

	public Location getLocation() {
		return location;
	}

	public Person getPerson() {
		return person;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

}
