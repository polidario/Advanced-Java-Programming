package org.bbpolidario.Users;

import org.bbpolidario.services.Configuration;
import org.bbpolidario.services.exceptions.CreationException;
import org.bbpolidario.services.exceptions.DatamodelCreationException;

import java.sql.*;

public class UsersDAO {
    public void create(User user) throws DatamodelCreationException {
        try {
            Connection connection = Configuration.getConnection();
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


    public void search(User user){

    }
}