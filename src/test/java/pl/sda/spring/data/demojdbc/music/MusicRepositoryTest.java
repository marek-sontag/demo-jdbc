package pl.sda.spring.data.demojdbc.music;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.spring.data.demojdbc.RepositoryConfig;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = RepositoryConfig.class)
public class MusicRepositoryTest {

    @Autowired
    MusicRepository musicRepository;

    @Test
    public void testMusiRepository() {
        Song acrobat = new Song();
        acrobat.setTitle("Acrobat");
        acrobat.setDurationInSec(300);

        Song one = new Song();
        one.setTitle("One");
        one.setDurationInSec(400);

        Album album = new Album();
        album.setTitle("Achtung baby");
        album.setSongs(Arrays.asList(acrobat, one));

        musicRepository.save(album);

        assertThat(musicRepository.findAll()).isNotEmpty();
        assertThat(musicRepository.countSongs()).isEqualTo(2);

        musicRepository.deleteAll();

        assertThat(musicRepository.countSongs()).isEqualTo(0);
    }
}
