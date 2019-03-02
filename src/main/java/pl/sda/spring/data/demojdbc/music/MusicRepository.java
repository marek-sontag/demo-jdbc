package pl.sda.spring.data.demojdbc.music;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<Album, Long> {

    @Query("select count(*) from SONG")
    int countSongs();
}
