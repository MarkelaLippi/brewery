package gmail.roadtojob2019.brewery.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {
    @Bean
    public SpringLiquibase liquibase (DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:migrations/db-changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}

