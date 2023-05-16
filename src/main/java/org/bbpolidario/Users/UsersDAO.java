package org.bbpolidario.Users;

import java.sql.*;

public class UsersDAO {
    public void create(User user) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "user", "user");

        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (?, ?)");
        insertStatement.setInt(1, user.getId());
        insertStatement.setString(2, user.getName());
        insertStatement.execute();
    }


    public void search(User user){

    }
}