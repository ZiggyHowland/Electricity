package nu.hovland.electricity;

import nu.hovland.electricity.services.MeeterServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        MeeterServiceImpl service = context.getBean(MeeterServiceImpl.class);

        service.findByLocation(1L)
                .stream()
                .forEach(m -> System.out.println(m.toString()));

    }

}
