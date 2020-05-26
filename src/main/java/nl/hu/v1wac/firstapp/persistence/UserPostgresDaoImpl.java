package nl.hu.v1wac.firstapp.persistence;

import nl.hu.v1wac.firstapp.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {
    PostgresBaseDao baseDao = new PostgresBaseDao();
    Connection conn = baseDao.getConnection();

    public UserPostgresDaoImpl() throws Exception {
    }

    public String findRoleForUser(String name, String pass) throws SQLException {
        String query = "SELECT role FROM useraccount WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getString("role");
        }
        return null;
    }
}
