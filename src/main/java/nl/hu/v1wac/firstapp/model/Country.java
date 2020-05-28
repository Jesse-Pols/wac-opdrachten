package nl.hu.v1wac.firstapp.model;

public class Country {
	private String code;
	private String iso3;
	private String name;
	private String capital;
	private String continent;
	private String region;
	private double surface;
	private int population;
	private String government;
	private double latitude;
	private double longitude;
	private String oldname;
	
	public Country(String code, String iso3, String nm, String cap, String ct, String reg, double sur, int pop, String gov, double lat, double lng) {
		this.code = code; 
		this.iso3 = iso3;
		this.name = nm;
		this.capital = cap;
		this.continent = ct;
		this.region = reg;
		this.surface = sur;
		this.population = pop;
		this.government = gov;
		this.latitude = lat;
		this.longitude = lng;
	}

	public Country() {}

	public Country(String name, String capital, String region, int population, double surface, String oldname) {
		this.oldname = oldname;
		this.name = name;
		this.capital = capital;
		this.region = region;
		this.population = population;
		this.surface = surface;
	}

	public Country(String code, String name, String capital, String region, int population, double surface) {
		this.code = code;
		this.name = name;
		this.capital = capital;
		this.region = region;
		this.population = population;
		this.surface = surface;
	}

	public String getCode() {
		return code;
	}
	
	public String getIso3() {
		return iso3;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCapital() {
		return capital;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public String getRegion() {
		return region;
	}
	
	public double getSurface() {
		return surface;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public String getGovernment() {
		return government;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public void setOldName(String name) {
		this.oldname = name;
	}

	public String getOldName() {
		return this.oldname;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public boolean checkIfEmpty() {
		if (this.name.isEmpty() || this.region.isEmpty() || this.capital.isEmpty() || this.code.isEmpty() || this.continent.isEmpty() || this.government.isEmpty() || this.iso3.isEmpty()) {
			return false;
		}
		return true;
	}
}
