package uitmp3API.music_player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uitmp3API.music_player.model.Country;
import uitmp3API.music_player.service.CountryService;

@RestController
@CrossOrigin
@RequestMapping("/api/countries")
public class CountryController {
	@Autowired
	CountryService counService;
	
	// create country
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Country createCountry(@RequestBody Country country) {
		return counService.createCountry(country);
	}
	
	// read all countries
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Country> getAllCountries(){
		return counService.getAllCountries();
	}
	
	// update country by id
	@RequestMapping(value = "/{countryId}", method = RequestMethod.PUT)
	public Country updateCountry(@PathVariable(value = "countryId") Long countryId, @RequestBody Country countryDetails) {
		return counService.updateCountry(countryId, countryDetails);
	}
}
