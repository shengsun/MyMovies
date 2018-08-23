package com.sandy.mymovies.repositories;

import com.sandy.mymovies.models.domain.Episode;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface EpisodeRepo extends CrudRepository<Episode, String> {

	// /episodes/{imdbid}
	List<Episode> findAllByImdbid(String imdbid);

	// /episodes/{imdbid}/{season}
	List<Episode> findAllByImdbidAndSeason(String imdbid, String season);

	// todo: ??
	// /seasons/{imdbid}
	@Query("SELECT DISTINCT season from Episode where imdbid=?1")
	List<String> findDistinctSeasonsByImdbid(String imdbid);
}
