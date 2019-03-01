package pl.sda.spring.data.demojdbc.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = PersonConfig.class)
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void createSimplePersonTest() {
        Person person = Person.builder()
                .firstName("John")
                .lastName("Tolkien")
                .dateOfBirth(LocalDate.of(1892, 1, 3))
                .proffession(Proffession.WRITER)
                .build();

        Person saved = personRepository.save(person);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getFirstName()).isEqualTo("John");
        assertThat(saved.getLastName()).isEqualTo("Tolkien");
        assertThat(saved.getProffession()).isEqualTo(Proffession.WRITER);
    }

    @Test
    public void modifyExistingPersonTest() {
        Person person = Person.builder()
                .firstName("John")
                .lastName("Tolkien")
                .dateOfBirth(LocalDate.of(1892, 1, 3))
                .proffession(Proffession.WRITER)
                .build();

        Person saved = personRepository.save(person);

        saved.setFirstName("Michael");
        saved.setLastName("Schumacher");
        saved.setDateOfBirth(LocalDate.of(1969, 1, 3));
        saved.setProffession(Proffession.F1_DRIVER);

        personRepository.save(saved);

        Optional<Person> found = personRepository.findById(saved.getId());

        assertThat(found).isNotEmpty();
        assertThat(found.get().getId()).isEqualTo(saved.getId());
        assertThat(found.get().getFirstName()).isEqualTo("Michael");
        assertThat(found.get().getLastName()).isEqualTo("Schumacher");
        assertThat(found.get().getProffession()).isEqualTo(Proffession.F1_DRIVER);
    }

    @Test
    public void findMichaelsTest() {
        Person person = Person.builder()
                .firstName("John")
                .lastName("Tolkien")
                .dateOfBirth(LocalDate.of(1892, 1, 3))
                .proffession(Proffession.WRITER)
                .build();

        List<Person> found = personRepository.findMichaels();

        assertThat(found).isEmpty();

        Person michael = Person.builder()
                .firstName("Michael")
                .lastName("Schumacher")
                .dateOfBirth(LocalDate.of(1969, 1, 3))
                .proffession(Proffession.F1_DRIVER)
                .build();

        personRepository.save(michael);

        found = personRepository.findMichaels();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isEqualTo(1);
    }

    @Test
    public void countWritersTest() {
        Person person = Person.builder()
                .firstName("John")
                .lastName("Tolkien")
                .dateOfBirth(LocalDate.of(1892, 1, 3))
                .proffession(Proffession.WRITER)
                .build();
        personRepository.save(person);

        Person michael = Person.builder()
                .firstName("Michael")
                .lastName("Schumacher")
                .dateOfBirth(LocalDate.of(1969, 1, 3))
                .proffession(Proffession.F1_DRIVER)
                .build();

        personRepository.save(michael);

        int writersCount = personRepository.countWriters();

        assertThat(writersCount).isEqualTo(1);
    }

}
