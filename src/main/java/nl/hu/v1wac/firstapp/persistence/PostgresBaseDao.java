package nl.hu.v1wac.firstapp.persistence;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

public class PostgresBaseDao {

    public Connection getConnection() throws Exception {
        return getDataSource().getConnection();
    }

    private InitialContext getInitialContext() throws NamingException {
        return new InitialContext();
    }

    private DataSource getDataSource(InitialContext cxt) throws NamingException {
        return (DataSource) cxt.lookup( "java:/comp/env/jdbc/PostgresDS" );
    }

    private DataSource getDataSource() throws Exception {
        InitialContext cxt = getInitialContext();
        if (cxt == null) throw new Exception("Uh oh == no context!");

        DataSource ds = getDataSource(cxt);
        if (ds == null) throw new Exception("Data source not found!");

        return ds;
    }

}
