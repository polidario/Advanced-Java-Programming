package org.bbpolidario.Users;

import org.bbpolidario.services.Configuration;
import org.bbpolidario.services.exceptions.DatamodelCreationException;
import org.bbpolidario.services.exceptions.DatamodelSearchException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private final DataSource source;

    public UsersDAO(DataSource source) {
        this.source = source;
    }

    public void createTable() throws DatamodelCreationException {
        try {
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE USERS(ID INT PRIMARY KEY, NAME VARCHAR(255))");
        } catch (Exception e) {
            DatamodelCreationException creationException = new DatamodelCreationException();
            creationException.initCause(e);
            throw creationException;
        }
    }

    public void create(User user) throws DatamodelCreationException {
        try {
            Connection connection = source.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (?, ?)");
            insertStatement.setInt(1, user.getId());
            insertStatement.setString(2, user.getName());
            insertStatement.execute();
        }catch (Exception e){
            DatamodelCreationException creationException = new DatamodelCreationException();
            creationException.initCause(e);
            throw creationException;
        }
    }


    public List search(User criteria) throws DatamodelSearchException {
        List users = new ArrayList();
        try {
            Connection connection = source.getConnection();
            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT ID,NAME FROM USERS WHERE ID = ? AND NAME = ?");
            selectStatement.setString(2, criteria.getName());
            selectStatement.setInt(1, criteria.getId());

            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                User user = new User(id, name);
                users.add(user);
            }
        }catch (SQLException e){
            DatamodelSearchException dse = new DatamodelSearchException();
            dse.initCause(e);
            throw dse;
        }
        return users;
    }
}