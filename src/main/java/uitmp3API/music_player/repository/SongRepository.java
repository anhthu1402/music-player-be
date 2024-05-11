package uitmp3API.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uitmp3API.music_player.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{

}
