package nu.hovland.electricity;

import nu.hovland.electricity.services.MeterServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        MeterServiceImpl service = context.getBean(MeterServiceImpl.class);

        service.findByLocation(1L)
                .stream()
                .forEach(m -> System.out.println(m.toString()));

    }

}
