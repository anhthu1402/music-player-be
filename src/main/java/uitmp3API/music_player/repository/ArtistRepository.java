package uitmp3API.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uitmp3API.music_player.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
	Artist findByArtistName(String artistName);
}
