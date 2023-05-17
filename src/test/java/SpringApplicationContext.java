import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationContext {
    @Bean("TestDependencyInjection")
    public String stringHello() {
        return "Hello World from Dependency Injection!";
    }
}
