package pl.sda.spring.data.demojdbc.person;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select count(*) from person where proffession = 'WRITER'")
    int countWriters();

    @Query("select * from person where first_name = 'Michael'")
    List<Person> findMichaels();
}
