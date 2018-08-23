package com.sandy.mymovies.controllers;
import com.sandy.mymovies.models.domain.Episode;
import com.sandy.mymovies.models.domain.Movie;
import com.sandy.mymovies.models.dto.Title;
import com.sandy.mymovies.services.MyMoviesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyMoviesController {

	private MyMoviesService service;

	// -- Basic crud endpoints --
	//		/title/{imdbid}
	//		/titles/{index}/{key}
	// 		/movies/{imdbid}
	//		/movies/{index}
	// 		/cast/{imdbid}
	// 		/episodes/{imdbid}/{season}
	// 		/episodes/{imdbid}
	// 		/season/{imdbid}
	// -- Index endpoints --
	//		/index/keys/{name}
	//		/index/{name}
	//		/index/{name}/{key}
	@Autowired
	public MyMoviesController(MyMoviesService service) {
		this.service = service;
	}

	/**
	 * @return Individual movie
	 * @param imdbid
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/movie/{imdbid}")
	@ResponseStatus(HttpStatus.OK)
	public Movie getMovieByImdbId(@PathVariable String imdbid) {
		return service.verifyMovie(imdbid);
	}

//	/**
//	 * @return Movies associated with an index
//	 * @param index
//	 */
//	@RequestMapping(method = RequestMethod.GET, path = "{/movies/{index}}")
//	@ResponseStatus(HttpStatus.OK)
//	public List<Movie> getMoviesByIndex(@PathVariable String index) {
//		return service.verifyMoviesByIndex(index);
//	}

	/**
	 * @return Individual movie title
	 * @param imdbid
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/title/{imdbid}")
	@ResponseStatus(HttpStatus.OK)
	public Title getTitleByImdbId(@PathVariable String imdbid) {
		return service.verifyTitle(imdbid);
	}

//	/**
//	 * @return Titles associated with an index
//	 * @param index
//	 * @param key
//	 */
//	@RequestMapping(method = RequestMethod.GET, path = "{/titles/{index}/{key}")
//	public List<Title> getTitlesByIndexAndKey(@PathVariable String index, @PathVariable String key)
//	{
//		return service.verifyTitlesByIndexAndKey(index, key);
//	}

//	/**
//	 * @return Cast associated with a single movie (list of strings)
//	 * @param imdbid
//	 */
//	@RequestMapping(method = RequestMethod.GET, path = "{/cast/{imdbid}}")
//	@ResponseStatus(HttpStatus.OK)
//	public Cast getCastByImdbId(@PathVariable String imdbid) {
//		return service.verifyCast(imdbid);
//	}

	/**
	 * @return All episodes associated with a show
	 * @param imdbid
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/episodes/{imdbid}")
	@ResponseStatus(HttpStatus.OK)
	public List<Episode> getEpisodesByImdbId(@PathVariable String imdbid) {
		return service.verifyEpisodes(imdbid);
	}

//	/**
//	 * @return Episodes associated with a show and season
//	 * @param imdbid
//	 */
//	@RequestMapping(method = RequestMethod.GET, path = "/episodes/{imdbid}/{seasonNumber}")
//	@ResponseStatus(HttpStatus.OK)
//	public List<Episode> getEpisodesByImdbIdAndSeason(@PathVariable String imdbid, @PathVariable seasonNumber) {
//		return service.verifyEpisodes(imdbid, seasonNumber);
//	}

	/**
	 * @return A list of seasons associated with a show
	 * @param imdbid
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/seasons/{imdbid}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getSeasonsByImdbId(@PathVariable String imdbid) {
		return service.verifyImdbAndReturnSeasons(imdbid);
	}

	// -- Remaining endpoints --
	//		/index/keys/{name}
	//		/index/{name}
	//		/index/{name}/{key}
	//		/search/{index}
	//		/count/{index}

}
