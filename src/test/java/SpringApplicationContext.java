import org.bbpolidario.Users.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringApplicationContext {
    @Bean("TestDependencyInjection")
    public String stringHello() {
        return "Hello World from Dependency Injection!";
    }

    @Bean
    public DataSource mainDatasource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test", "user","user");
    }

    @Bean
    public UsersDAO dao(@Autowired DataSource dataSource){
        return new UsersDAO(dataSource);
    }
}
