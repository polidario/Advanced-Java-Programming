import org.bbpolidario.Users.User;
import org.bbpolidario.Users.UsersDAO;
import org.bbpolidario.services.exceptions.DatamodelCreationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;

public class TestConnectionClass {
    private Connection connection;
    private static DriverManagerDataSource ds;

    @BeforeAll
    public static void before() {
        ds = new DriverManagerDataSource("jdbc:h2:mem:test", "user","user");
    }

    @BeforeEach
    public void setup() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:test", "user", "user");
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE USERS(ID INT, NAME VARCHAR(255))");
        preparedStatement.execute();

    }

    @Test
    public void testCreate() throws DatamodelCreationException, SQLException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO(ds);
        User user = new User(1, "Thomas");


        //when
        dao.create(user);

        //then
        ResultSet resultSet = this.connection.prepareStatement("SELECT * FROM USERS WHERE ID = 1").executeQuery();
        String name = null;
        while (resultSet.next()){
            name = resultSet.getString("NAME");
        }

        Assertions.assertEquals(name, "Thomas");

    }

    @Test
    public void testSearch() throws SQLException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO(ds);
        User user = new User(1, "Thomas");

        //when
        PreparedStatement selectStatement = connection.prepareStatement("SELECT ID,NAME FROM USERS");
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            System.out.println(id + " " + name);
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.prepareStatement("DROP TABLE USERS").execute();
        connection.close();
    }
}
