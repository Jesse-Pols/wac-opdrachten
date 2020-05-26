package nl.hu.v1wac.firstapp.persistence;

import nl.hu.v1wac.firstapp.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryPostgresDaoImpl implements CountryDao {
    PostgresBaseDao baseDao = new PostgresBaseDao();
    Connection conn = baseDao.getConnection();
    CountryPostgresDaoImplService service = new CountryPostgresDaoImplService();

    public CountryPostgresDaoImpl() throws Exception {
    }

    /**
     * Save country by countrymodel
     *
     * @param country
     * @return true if success
     * @throws SQLException
     */
    public Boolean save(Country country) throws SQLException {
        String query = "INSERT INTO country(code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = service.setCountryToPreparedStatement(country, conn.prepareStatement(query));
        return service.executePreparedStatementWithBoolean(ps);
    }

    /**
     * Find all the countries
     *
     * @return list of countries
     * @throws SQLException
     */
    public List<Country> findAll() throws SQLException {
        String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country";
        PreparedStatement ps = conn.prepareStatement(query);
        List<Country> countries = service.getCountriesFromResultSet(ps.executeQuery());
        ps.close();
        return countries;
    }

    /**
     * Find country by countrycode
     *
     * @param code
     * @return country
     * @throws SQLException
     */
    public Country findByCode(String code) throws SQLException {
        String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country WHERE code = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, code);
        Country country = service.getCountriesFromResultSet(ps.executeQuery()).get(0);
        ps.close();
        return country;
    }

    /**
     * Find the top 10 countries with the largest populations
     * @return A list of countries
     * @throws SQLException
     */
    public List<Country> find10LargestPopulations() throws SQLException {
        String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country ORDER BY population DESC";
        PreparedStatement ps = conn.prepareStatement(query);
        List<Country> countries = service.getCountriesFromResultSet(ps.executeQuery());
        ps.close();

        List<Country> out = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            out.add(countries.get(i));
        }
        return out;
    }

    /**
     * Find the top 10 countries with the largest surfacearea's.
     * @return A list of countries
     * @throws SQLException
     */
    public List<Country> find10LargestSurfaces() throws SQLException {
        String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country ORDER BY surfacearea DESC";
        PreparedStatement ps = conn.prepareStatement(query);
        List<Country> countries = service.getCountriesFromResultSet(ps.executeQuery());
        ps.close();

        List<Country> out = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            out.add(countries.get(i));
        }
        return out;
    }

    /**
     * Update a country by code
     * @param country
     * @return true if success
     * @throws SQLException
     */
    public Boolean update(Country country) throws SQLException {
        String query = "UPDATE country SET code = ?, iso3 = ?, name = ?, capital = ?, continent = ?, region = ?, surfacearea = ?, population = ?, governmentform = ?, latitude = ?, longitude = ? WHERE code = ?";
        PreparedStatement ps = service.setCountryToPreparedStatement(country, conn.prepareStatement(query));
        ps.setString(12, country.getCode());
        return service.executePreparedStatementWithBoolean(ps);
    }

    /**
     * Delete a country by code
     * @param country
     * @return true if success
     * @throws SQLException
     */
    public Boolean delete(Country country) throws SQLException {
        // This could be easier with just the code as parameter. The interface tells us to use a Country Object though.
        String query = "DELETE FROM country WHERE code = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, country.getCode());
        return service.executePreparedStatementWithBoolean(ps);
    }

    /**
     * Find a country by name
     * @param name
     * @return country
     * @throws SQLException
     */
    public Country findByName(String name) throws SQLException {
        String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country WHERE name = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        Country country = service.getCountriesFromResultSet(ps.executeQuery()).get(0);
        ps.close();
        return country;
    }
}

class CountryPostgresDaoImplService {
    /**
     * Get all countries from a resultset
     * @param rs
     * @return List of countries
     * @throws SQLException
     */
    List<Country> getCountriesFromResultSet(ResultSet rs) throws SQLException {
        List<Country> countries = new ArrayList<>();

        while (rs.next()) {
            countries.add(new Country(
                    rs.getString("code"),
                    rs.getString("iso3"),
                    rs.getString("name"),
                    rs.getString("capital"),
                    rs.getString("continent"),
                    rs.getString("region"),
                    rs.getDouble("surfacearea"),
                    rs.getInt("population"),
                    rs.getString("governmentform"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude")));
        }

        rs.close();
        return countries;
    }

    /**
     * Add country parameters to preparedstatement
     * @param country
     * @param ps
     * @return filled preparedstatement
     * @throws SQLException
     */
    PreparedStatement setCountryToPreparedStatement(Country country, PreparedStatement ps) throws SQLException {
        ps.setString(1, country.getCode());
        ps.setString(2, country.getIso3());
        ps.setString(3, country.getName());
        ps.setString(4, country.getCapital());

        ps.setString(5, country.getContinent());
        ps.setString(6, country.getRegion());
        ps.setDouble(7, country.getSurface());
        ps.setInt(8, country.getPopulation());

        ps.setString(9, country.getGovernment());
        ps.setDouble(10, country.getLatitude());
        ps.setDouble(11, country.getLongitude());
        return ps;
    }

    /**
     * Execute and close the preparedstatements which were supposed to return a boolean
     * @param ps
     * @return true if success
     * @throws SQLException
     */
    Boolean executePreparedStatementWithBoolean(PreparedStatement ps) throws SQLException {
        Boolean success = !ps.execute();
        ps.close();
        return success;
    }
}