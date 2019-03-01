package pl.sda.spring.data.demojdbc.person;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJdbcRepositories
public class PersonConfig extends JdbcConfiguration {

    @Bean
    NamedParameterJdbcOperations operations() { // używany wewnętrznie żeby się odwoływać do bazy importantntne
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    PlatformTransactionManager transactionManager() { // theoretically not necessary, but do you want? :)
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("scripts/create-person-schema.sql")
                .build();
    }
}
