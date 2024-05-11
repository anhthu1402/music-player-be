package uitmp3API.music_player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uitmp3API.music_player.dto.AlbumSongDto;
import uitmp3API.music_player.dto.AlbumUpdateDto;
import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.service.AlbumService;

@RestController
@CrossOrigin
@RequestMapping("/api/albums")
public class AlbumController {

	@Autowired
	AlbumService albService;
	
	// create album
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Album createAlbum(@RequestBody Album album) {
		return albService.createAlbum(album);
	}
	
	// read all albums
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Album> getAllAlbums(){
		return albService.getAllAlbums();
	}
	
	// read album by id
	@RequestMapping(value = "/{albumId}", method = RequestMethod.GET)
	public AlbumSongDto getAlbumById(@PathVariable(value = "albumId") Long albumId) {
		return albService.getAlbumById(albumId);
	}
	
	// add artist
	@RequestMapping(value = "/{albumId}/artist/{artistId}", method = RequestMethod.PUT)
	public Album addArtist(@PathVariable(value = "albumId") Long albumId, @PathVariable(value = "artistId") Long artistId) {
		return albService.addArtist(albumId, artistId);
	}
	
	// add country
	@RequestMapping(value = "/{albumId}/country/{countryId}", method = RequestMethod.PUT)
	public Album addCountry(@PathVariable(value = "albumId") Long albumId, @PathVariable(value = "countryId") Long countryId) {
		return albService.addCountry(albumId, countryId);
	}
	
	// update album by id
	@RequestMapping(value = "/{albumId}", method = RequestMethod.PUT)
	public Album updateAlbum(@PathVariable(value = "albumId") Long albumId, @RequestBody AlbumUpdateDto albumDetails) {
		return albService.updateAlbum(albumId, albumDetails);
	}
	
	// delete album
	@RequestMapping(value = "/{albumId}", method = RequestMethod.DELETE)
	public Boolean deleteAlbum(@PathVariable(value = "albumId") Long albumId) {
		return albService.deleteAlbum(albumId);
	}
	
	// get number of albums
	@RequestMapping(value = "/number-of-albums", method = RequestMethod.GET)
	public Long getNumberOfAlbums() {
		return albService.getNumberOfAlbums();
	}
}
