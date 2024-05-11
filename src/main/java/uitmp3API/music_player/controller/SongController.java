package uitmp3API.music_player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uitmp3API.music_player.dto.SongCreateDto;
import uitmp3API.music_player.dto.SongDto;
import uitmp3API.music_player.dto.SongUpdateDto;
import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Song;
import uitmp3API.music_player.service.SongService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/songs")
public class SongController {
	@Autowired
	SongService songService;
	
	// create song
	@RequestMapping(value = "/album/{albumId}", method = RequestMethod.POST)
	public Song createSong(@RequestBody Song songDetails, @PathVariable Long albumId) {
		return songService.createSong(songDetails, albumId);
	}
	
	// read all songs
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Song> getAllSongs(){
		return songService.getAllSongs();
	}
	
	// read song by id
	@RequestMapping(value = "/{songId}", method = RequestMethod.GET)
	public SongDto getSongById(@PathVariable(value = "songId") Long id) {
		return songService.getSongById(id);
	}
	
	// add representation
	@RequestMapping(value = "/{songId}/representation/{artistId}", method = RequestMethod.PUT)
	public Song addRepresentation(@PathVariable(value = "songId") Long songId, @PathVariable(value = "artistId") Long artistId) {
		return songService.addRepresentation(songId, artistId);
	}
	
	// add composer
	@RequestMapping(value = "/{songId}/composer/{composerId}", method = RequestMethod.PUT)
	public Song addComposer(@PathVariable(value = "songId") Long songId, @PathVariable(value = "composerId") Long composerId) {
		return songService.addComposer(songId, composerId);
	}
	
	// read songs by singerId
	@RequestMapping(value = "/representation/{singerId}", method = RequestMethod.GET)
	public List<Song> getSongsBySingerId(@PathVariable(value = "singerId") Long singerId){
		return songService.getSongsBySingerId(singerId);
	}
	
	// read songs by composerId
	@RequestMapping(value = "/composing/{composerId}", method = RequestMethod.GET)
	public List<Song> getSongsByComposerId(@PathVariable(value = "composerId") Long composerId){
		return songService.getSongsByComposerId(composerId);
	}
	
	// read songs by countryId
	@RequestMapping(value = "/country/{countryId}", method = RequestMethod.GET)
	public List<Song> getSongsByCountryId(@PathVariable(value = "countryId") Long countryId){
		return songService.getSongsByCountryId(countryId);
	}
	
	// get song's album
	@RequestMapping(value = "/{songId}/album", method = RequestMethod.GET)
	public Album getSongAlbum(@PathVariable(value = "songId") Long songId) {
		return songService.getSongAlbum(songId);
	}
	
	// add album 
	@RequestMapping(value = "/{songId}/album/{albumId}", method = RequestMethod.PUT)
	public Song addAlbum(@PathVariable(value = "songId") Long songId, @PathVariable(value = "albumId") Long albumId) {
		return songService.addAlbum(songId, albumId);
	}
	
	// add country
	@RequestMapping(value = "/{songId}/country/{countryId}", method = RequestMethod.PUT)
	public Song addCountry(@PathVariable(value = "songId") Long songId, @PathVariable(value = "countryId") Long countryId) {
		return songService.addCountry(songId, countryId);
	}
	
	// delete song
	@RequestMapping(value = "/{songId}", method = RequestMethod.DELETE)
	public Boolean deleteSong(@PathVariable(value = "songId") Long songId) {
		return songService.deleteSong(songId);
	}
	
	// update song
	@RequestMapping(value = "/{songId}", method = RequestMethod.PUT)
	public Song updateSong(@PathVariable(value = "songId") Long songId, @RequestBody SongUpdateDto songDetails) {
		return songService.updateSong(songId, songDetails);
	}
	
	// get number of songs
	@RequestMapping(value = "/number-of-songs", method = RequestMethod.GET)
	public Long getNumberOfSongs() {
		return songService.getNumberOfSongs();
	}
}
