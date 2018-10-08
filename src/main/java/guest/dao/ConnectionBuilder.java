package guest.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by user on 08.10.2018.
 */
public interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}
