package nl.hu.v1wac.firstapp.persistence;

import java.sql.SQLException;

public interface UserDao {
    String findRoleForUser(String name, String pass) throws SQLException;
}
