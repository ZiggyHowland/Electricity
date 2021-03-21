package nu.hovland.electricity.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@Component
public class SeedDb {
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Autowired
    public SeedDb(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update("DELETE FROM Meeter;" );
        jdbcTemplate.update("DELETE FROM Location;");

        jdbcTemplate.update("INSERT INTO Location (id, street, houseNumber, houseSection, postCode) VALUES (?,?,?,?,?)",
                new Object[]{ 1L, "Titlestadvegen", "219", null, "5243" });

        jdbcTemplate.update("INSERT INTO Meeter (description, locationId) VALUES (?,?)",
                new Object[]{ "Hovedmåler", 1L });
        jdbcTemplate.update("INSERT INTO Meeter (description, locationId) VALUES (?,?)",
                new Object[]{ "Leilighet", 1L });
    }
}
