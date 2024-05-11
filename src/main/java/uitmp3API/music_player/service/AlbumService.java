package uitmp3API.music_player.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uitmp3API.music_player.dto.AlbumSongDto;
import uitmp3API.music_player.dto.AlbumUpdateDto;
import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.Country;
import uitmp3API.music_player.model.Song;
import uitmp3API.music_player.model.User;
import uitmp3API.music_player.repository.AlbumRepository;
import uitmp3API.music_player.repository.ArtistRepository;
import uitmp3API.music_player.repository.CountryRepository;
import uitmp3API.music_player.repository.SongRepository;

@Service
public class AlbumService {

	@Autowired
	AlbumRepository albRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	SongRepository songRepository;
	
	// create album
	public Album createAlbum(Album album) {
		return albRepository.save(album);
	}
	
	// read all albums
	public List<Album> getAllAlbums(){
		return albRepository.findAll();
	}
	
	// read album by id
	public AlbumSongDto getAlbumById(Long id) {
		Optional<Album> album = albRepository.findById(id);
		if(album.isPresent()) {
			return new AlbumSongDto(album.get());
		}
		return new AlbumSongDto();
	}
	
	// add artist
	public Album addArtist(Long albumId, Long artistId) {
		try {
			Album album = albRepository.findById(albumId).get();
			Artist artist = artistRepository.findById(artistId).get();
			
			album.addArtist(artist);
			artist.addAlbum(album);
			artistRepository.save(artist);
			return albRepository.save(album);
		} catch (Exception e) {
			return new Album();
		}
	}
	
	// add country
	public Album addCountry(Long albumId, Long countryId) {
		try {
			Album album = albRepository.findById(albumId).get();
			Country country = countryRepository.findById(countryId).get();
			
			album.setCountry(country);
			country.addAlbum(album);
			countryRepository.save(country);
			return albRepository.save(album);
		} catch (Exception e) {
			return new Album();
		}
	}
	
	// update list artists
	public void updateListArtists(Long albumId, List<Long> artistId) {
		Optional<Album> a = albRepository.findById(albumId);
		if(a.isPresent()) {
			Album album = a.get();
			List<Artist> list = new ArrayList<Artist>();
			album.setArtist(list);
			for (Long id : artistId) {
				Artist artist = artistRepository.findById(id).get();
				list.add(artist);
			}
			album.setArtist(list);
			albRepository.save(album);
		}
	}
	
	// update album by id
	public Album updateAlbum(Long albumId, AlbumUpdateDto albumDetails) {
		Optional<Album> album = albRepository.findById(albumId);
		Album alb = new Album();
		if(album.isPresent()) {
			alb = album.get();
		}
		alb.setAlbumImage(albumDetails.getAlbumImage());
		alb.setAlbumName(albumDetails.getAlbumName());
		alb.setInterestTimes(albumDetails.getInterestTimes());
		alb.setReleaseDate(albumDetails.getReleaseDate());
		
		Country country = countryRepository.findById(albumDetails.getCountryId()).get();
		alb.setCountry(country);
		
		updateListArtists(albumId, albumDetails.getArtistId());
		
		return albRepository.save(alb);
	}
	
	// delete album 
	public Boolean deleteAlbum(Long albumId) {
		Optional<Album> a = albRepository.findById(albumId);
		if(a.isPresent()) {
			Album album = a.get();
			List<Artist> artists = album.getArtist();
			for (Artist artist : artists) {
				artist.removeAlbum(album);
			}
			List<Song> songs = album.getSongs();
			for (Song song : songs) {
				song.setAlbum(null);
			}
			List<User> users = album.getFavAlbums();
			for (User user : users) {
				user.removeFavAlbum(album);
			}
			albRepository.delete(album);
			return true;
		}
		return false;
	}
	
	// get number of albums
	public Long getNumberOfAlbums() {
		return albRepository.count();
	}
}
