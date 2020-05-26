package nl.hu.v1wac.firstapp.persistence;

import nl.hu.v1wac.firstapp.model.Country;

import java.sql.SQLException;
import java.util.List;

public interface CountryDao {
    Boolean save(Country country) throws SQLException;
    List<Country> findAll() throws SQLException;
    Country findByCode(String code) throws SQLException;
    List<Country> find10LargestPopulations() throws SQLException;
    List<Country> find10LargestSurfaces() throws SQLException;
    Boolean update(Country country) throws SQLException;
    Boolean delete(Country country) throws SQLException;
    Country findByName(String name) throws SQLException;
}
