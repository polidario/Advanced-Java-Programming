import org.bbpolidario.Users.UsersDAO;
import org.bbpolidario.Users.User;
import org.bbpolidario.services.exceptions.DatamodelCreationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringApplicationContext.class)
public class TestSpring {
    @Inject
    @Named("TestDependencyInjection")
    String HelloWorld;

    @Inject
    UsersDAO dao;

    @Test
    public void test() throws DatamodelCreationException {
        System.out.println(HelloWorld);
        User user = new User(1, "Thomas");
        dao.createTable();
        dao.create(user);
    }
}
