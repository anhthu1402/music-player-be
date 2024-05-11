package uitmp3API.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uitmp3API.music_player.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

}
