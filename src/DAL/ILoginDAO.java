package DAL;

import BE.User;

import java.sql.SQLException;
import java.util.List;

public interface ILoginDAO {
    List<User> getAllUsers() throws SQLException;
}
