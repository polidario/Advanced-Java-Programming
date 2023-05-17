import org.bbpolidario.Users.UsersDAO;
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
    @Named("TestUserDependencyInjection")
    UsersDAO helloUser;

    @Test
    public void test() {
        System.out.println(HelloWorld);
        System.out.println(helloUser);
    }
}
