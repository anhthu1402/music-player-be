package uitmp3API.music_player.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uitmp3API.music_player.dto.ArtistAlbumDto;
import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.Song;
import uitmp3API.music_player.model.User;
import uitmp3API.music_player.repository.AlbumRepository;
import uitmp3API.music_player.repository.ArtistRepository;
import uitmp3API.music_player.repository.SongRepository;

@Service
public class ArtistService {
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	AlbumRepository albRepository;
	
	@Autowired
	SongRepository songRepository;
	
	// create artist
	public Artist createArtist(Artist a) {
		return artistRepository.save(a);
	}
	
	// read all artists
	public List<Artist> getAllArtists(){
		return artistRepository.findAll();
	}
	
	// read artist by id
	public Artist getArtistById(Long id) {
		Optional<Artist> artist = artistRepository.findById(id);
		if(artist.isPresent()) {
			return artist.get();
		}
		return new Artist();
	}
	
	// add album
	public ArtistAlbumDto addAlbum(Long atistId, Long albumId) {
		try {
			Artist artist = artistRepository.findById(atistId).get();
			Album album = albRepository.findById(albumId).get();
			
			artist.addAlbum(album);
			album.addArtist(artist);
			artistRepository.save(artist);
			albRepository.save(album);
			return new ArtistAlbumDto(artist);
		} catch (Exception e) {
			return new ArtistAlbumDto();
		}
	}
	
	// read artist's albums
	public List<Album> getArtistAlbums(Long artistId) {
		List<Album> albums = albRepository.findAll();
		List<Album> result = new ArrayList<Album>();
		for (Album album : albums) {
			List<Artist> artists = album.getArtist();
			for (Artist artist : artists) {
				if(artist.getId() == artistId) {
					result.add(album);
				}
			}
		}
		return result;
	}
	
	// update artist by id
	public Artist updateArtist(Long artistId, Artist artistDetails) {
		Optional<Artist> artist = artistRepository.findById(artistId);
		if(artist.isPresent()) {
			Artist a = artist.get();
			a.setArtistName(artistDetails.getArtistName());
			a.setArtistImage(artistDetails.getArtistImage());
			a.setIntroduce(artistDetails.getIntroduce());
			a.setNumberOfFollower(artistDetails.getNumberOfFollower());
			return artistRepository.save(a);
		}
		return artistRepository.save(artistDetails);
	}
	
	// delete artist
	public Boolean deleteArtist(Long artistId) {
		Optional<Artist> a = artistRepository.findById(artistId);
		if(a.isPresent()) {
			Artist artist = a.get();
			if(artist.getId() == 17) {
				return false;
			}
			else {
				List<Album> albums = artist.getArtistAlbum();
				for (Album album : albums) {
					if(album.getArtist().size() == 1) {
						Artist va = artistRepository.findById((long) 17).get();
						album.addArtist(va);
					}
					album.removeArtist(artist);
				}
				List<Song> representations = artist.getRepresentation();
				for (Song song : representations) {
					if(song.getRepresentation().size() == 1) {
						Artist va = artistRepository.findById((long) 17).get();
						song.addRepresentation(va);
					}
					song.removeRepresentation(artist);
				}
				List<Song> composers = artist.getComposing();
				for (Song song : composers) {
					if(song.getRepresentation().size() == 1) {
						Artist va = artistRepository.findById((long) 17).get();
						song.addComposer(va);
					}
					song.removeComposer(artist);
				}
				List<User> users = artist.getFollowers();
				for (User user : users) {
					user.unfollow(artist);
				}
				artistRepository.delete(artist);
				return true;
			}
		}
		return false;
	}
	
	// get number of artists
	public Long getNumberOfArtists() {
		return artistRepository.count();
	}
}
