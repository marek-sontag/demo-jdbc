package pl.sda.spring.data.demojdbc.music;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = "songs")
public class Album {

    @Id Long id;
    String title;
    List<Song> songs;
}
