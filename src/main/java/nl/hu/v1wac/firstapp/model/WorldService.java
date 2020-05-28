package nl.hu.v1wac.firstapp.model;

import nl.hu.v1wac.firstapp.persistence.CountryDao;
import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorldService {

	CountryDao countryDao = new CountryPostgresDaoImpl();

	public WorldService() throws Exception {
	}

	public List<Country> getAllCountries() {
		try {
			return countryDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public List<Country> get10LargestPopulations() {
		try {
			return countryDao.find10LargestPopulations();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<Country> get10LargestSurfaces() {
		try {
			return countryDao.find10LargestSurfaces();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public Country getCountryByCode(String code) {
		try {
			return countryDao.findByCode(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean deleteCountryByCode(String code) {
		try {
			Country country = countryDao.findByCode(code);
			return countryDao.delete(country);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean updateCountry(Country country) {
		try {
			Country existingCountry = countryDao.findByName(country.getOldName());
			existingCountry.setName(country.getName());
			existingCountry.setCapital(country.getCapital());
			existingCountry.setPopulation(country.getPopulation());
			existingCountry.setSurface(country.getSurface());
			existingCountry.setRegion(country.getRegion());

			return countryDao.update(existingCountry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean saveCountry(Country country) {
		try {
			return countryDao.save(country);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
