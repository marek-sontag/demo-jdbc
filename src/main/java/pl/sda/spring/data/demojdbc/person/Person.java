package pl.sda.spring.data.demojdbc.person;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Builder
public class Person {

    @Id
    Long id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    Proffession proffession;
}
