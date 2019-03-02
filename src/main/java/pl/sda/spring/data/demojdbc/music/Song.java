package pl.sda.spring.data.demojdbc.music;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Song {

    @Id
    Long id;
    String title;
    int durationInSec;
}
