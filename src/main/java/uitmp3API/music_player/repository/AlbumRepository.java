package uitmp3API.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uitmp3API.music_player.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{
	Album findByAlbumName(String albumName);
}
