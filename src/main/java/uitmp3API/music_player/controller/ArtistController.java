package uitmp3API.music_player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uitmp3API.music_player.dto.ArtistAlbumDto;
import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.service.ArtistService;

@RestController
@CrossOrigin
@RequestMapping("/api/artists")
public class ArtistController {
	@Autowired
	ArtistService artistService;
	
	// create artist
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Artist createArtist(@RequestBody Artist artist) {
		return artistService.createArtist(artist);
	}
	
	// read all artists
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Artist> getAllArtists(){
		return artistService.getAllArtists();
	}
	
	// read artist by id
	@RequestMapping(value = "/{artistId}", method = RequestMethod.GET)
	public Artist getArtistById(@PathVariable(value = "artistId") Long id) {
		return artistService.getArtistById(id);
	}
	
	// add album
	@RequestMapping(value = "/{artistId}/album/{albumId}", method = RequestMethod.PUT)
	public ArtistAlbumDto addAlbum(@PathVariable(value = "artistId") Long artistId, @PathVariable(value = "albumId") Long albumId) {
		return artistService.addAlbum(artistId, albumId);
	}
	
	// read artist's albums
	@RequestMapping(value = "/{artistId}/albums", method = RequestMethod.GET)
	public List<Album> getArtistAlbums(@PathVariable(value = "artistId") Long artistId){
		return artistService.getArtistAlbums(artistId);
	}
	
	// delete artist
	@RequestMapping(value = "/{artistId}", method = RequestMethod.DELETE)
	public Boolean deleteArtist(@PathVariable(value = "artistId") Long artistId) {
		return artistService.deleteArtist(artistId);
	}
	
	// update artist by id
	@RequestMapping(value = "/{artistId}", method = RequestMethod.PUT)
	public Artist updateArtist(@PathVariable(value = "artistId") Long artistId, @RequestBody Artist artistDetails) {
		return artistService.updateArtist(artistId, artistDetails);
	}
	
	// get number of songs
	@RequestMapping(value = "/number-of-artists", method = RequestMethod.GET)
	public Long getNumberOfArtists() {
		return artistService.getNumberOfArtists();
	}
}
