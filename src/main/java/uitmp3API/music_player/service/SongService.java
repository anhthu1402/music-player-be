package uitmp3API.music_player.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uitmp3API.music_player.dto.SongDto;
import uitmp3API.music_player.dto.SongUpdateDto;
import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.Country;
import uitmp3API.music_player.model.Playlist;
import uitmp3API.music_player.model.Song;
import uitmp3API.music_player.model.User;
import uitmp3API.music_player.repository.AlbumRepository;
import uitmp3API.music_player.repository.ArtistRepository;
import uitmp3API.music_player.repository.CountryRepository;
import uitmp3API.music_player.repository.SongRepository;

@Service
public class SongService {
	@Autowired
	SongRepository songRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	// create song
	public Song createSong(Song songDetails, Long albumId) {
		Optional<Album> a = albumRepository.findById(albumId);
		if(a.isPresent()) {
			Album album = a.get();
			songDetails.setAlbum(album);
			return songRepository.save(songDetails);
		}
		return new Song();
	}
	
	// read all songs
	public List<Song> getAllSongs(){
		return songRepository.findAll();
	}
	
	// read song by id
	public SongDto getSongById(Long id) {
		Optional<Song> song = songRepository.findById(id);
		if(song.isPresent()) {
			return new SongDto(song.get());
		}
		return new SongDto();
	}
	
	// add representation
	public Song addRepresentation(Long songId, Long artistId) {
		try {
			Song song = songRepository.findById(songId).get();
			Artist artist = artistRepository.findById(artistId).get();
			
			List<Artist> artists = song.getRepresentation();
			for (Artist rep : artists) {
				if(rep.getId() == artistId) {
					return song;
				}
			}
			song.addRepresentation(artist);
			artist.addSongRepresentation(song);
			songRepository.save(song);
			artistRepository.save(artist);
			return song;
		} catch (Exception e) {
			return new Song();
		}
	}
	
	// add representation
	public void addRepresentation(Song song, List<Long> artistsId) {
		List<Artist> list = new ArrayList<Artist>();
		song.setRepresentation(list);
		for (Long artistId : artistsId) {
			Artist artist = artistRepository.findById(artistId).get();
			song.addRepresentation(artist);
			artist.addSongRepresentation(song);
			songRepository.save(song);
			artistRepository.save(artist);
		}
		
	}
	
	// add composer
	public void addComposer(Song song, List<Long> composersId) {
		List<Artist> list = new ArrayList<Artist>();
		song.setComposing(list);
		for (Long composerId : composersId) {
			Artist composer = artistRepository.findById(composerId).get();
			song.addComposer(composer);
			composer.addSongComposing(song);
			songRepository.save(song);
			artistRepository.save(composer);
		}
	}
	
	// add composing
	public Song addComposer(Long songId, Long composerId) {
		try {
			Song song = songRepository.findById(songId).get();
			Artist composer = artistRepository.findById(composerId).get();
			List<Artist> artists = song.getRepresentation();
			for (Artist rep : artists) {
				if(rep.getId() == composerId) {
					return song;
				}
			}
			song.addComposer(composer);
			composer.addSongComposing(song);
			songRepository.save(song);
			artistRepository.save(composer);
			return song;
		} catch (Exception e) {
			return new Song();
		}
	}
	
	// read songs by singerId
	public List<Song> getSongsBySingerId(Long singerId) {
		List<Song> songs = songRepository.findAll();
		List<Song> result = new ArrayList<Song>();
		
		for (Song song : songs) {
			List<Artist> singers = song.getRepresentation();
			for (Artist singer : singers) {
				if(singer.getId()==singerId) {
					result.add(song);
				}
			}
		}
		return result;
	}
	
	// read songs by composerId
	public List<Song> getSongsByComposerId(Long composerId) {
		List<Song> songs = songRepository.findAll();
		List<Song> result = new ArrayList<Song>();
		
		for (Song song : songs) {
			List<Artist> composers = song.getComposing();
			for (Artist composer : composers) {
				if(composer.getId()== composerId) {
					result.add(song);
				}
			}
		}
		return result;
	}
	
	// read songs by countryId
	public List<Song> getSongsByCountryId(Long countryId){
		List<Song> songs = songRepository.findAll();
		List<Song> result = new ArrayList<Song>();
		
		for (Song song : songs) {
			if(song.getCountry().getId() == countryId) {
				result.add(song);
			}
		}
		return result;
	}
	
	// get song's album
	public Album getSongAlbum(Long songId) {
		Optional<Song> song = songRepository.findById(songId);
		if(song.isPresent()) {
			Song s = song.get();
			Album album = s.getAlbum();
			if(album != null) {
				return new Album(album);
			}
			return new Album();
		}
		return new Album();
	}
	
	// add album
	public Song addAlbum(Long songId, Long albumId) {
		try {
			Song song = songRepository.findById(songId).get();
			Album album = albumRepository.findById(albumId).get();
			
			song.setAlbum(album);
			return songRepository.save(song);
		} catch (Exception e) {
			return new Song();
		}
	}
	
	// add country
	public Song addCountry(Long songId, Long countryId) {
		try {
			Song song = songRepository.findById(songId).get();
			Country country = countryRepository.findById(countryId).get();
			
			song.setCountry(country);
			return songRepository.save(song);
		} catch (Exception e) {
			return new Song();
		}
	}
	
	// update song
	public Song updateSong(Long songId, SongUpdateDto songDetails) {
		Optional<Song> song = songRepository.findById(songId);
		Song s = new Song();
		if(song.isPresent()) {
			s = song.get();
		}
		s.setSongImage(songDetails.getSongImage());
		s.setSongLink(songDetails.getSongLink());
		s.setSongName(songDetails.getSongName());
		s.setLyrics(songDetails.getLyrics());
		s.setReleaseDate(songDetails.getReleaseDate());
		s.setTimeLimit(songDetails.getTimeLimit());
		s.setCountry(songDetails.getCountry());
		
		addRepresentation(s, songDetails.getRepresentationId());
		
		addComposer(s, songDetails.getComposersId());
		return songRepository.save(s);
	}
	// delete song
	public Boolean deleteSong(Long songId) {
		Optional<Song> s = songRepository.findById(songId);
		if(s.isPresent()) {
			Song song = s.get();
			List<Artist> representations = song.getRepresentation();
			for (Artist artist : representations) {
				artist.removeSongRepresentation(song);
			}
			List<Artist> composings = song.getComposing();
			for (Artist artist : composings) {
				artist.removeSongComposing(song);
			}
			song.setAlbum(null);
			List<User> users = song.getFavSongs();
			for (User user : users) {
				user.removeFavSong(song);
			}
			List<Playlist> playlists = song.getPlaylists();
			for (Playlist playlist : playlists) {
				playlist.removeSongPlaylist(song);
			}
			song.setCountry(null);
			songRepository.delete(song);
			return true;
		}
		return false;
	}
	
	// get number of songs
	public Long getNumberOfSongs() {
		return songRepository.count();
	}
}
