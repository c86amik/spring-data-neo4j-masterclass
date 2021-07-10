/**
 * 
 */
package com.springcavaj.springneo4j.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.springcavaj.springneo4j.exception.SpringDataNeo4jRestException;
import com.springcavaj.springneo4j.model.Movie;
import com.springcavaj.springneo4j.model.MovieRequest;
import com.springcavaj.springneo4j.model.Person;
import com.springcavaj.springneo4j.model.Role;
import com.springcavaj.springneo4j.repository.SpringDataNeo4jMovieRestRepository;
import com.springcavaj.springneo4j.repository.SpringDataNeo4jPersonRestRepository;

/**
 * @author springcavaj
 *
 */
@Service
public class SpringDataNeo4jMovieService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataNeo4jMovieService.class);
	
	@Autowired
	private SpringDataNeo4jPersonRestRepository personRestRepository;
	
	@Autowired
	private SpringDataNeo4jMovieRestRepository movieRestRepository;
	
	public List<Movie> getAllMovies() {
		LOGGER.info("getAllMovies in Service class called");
		return movieRestRepository.findAll();
    }
	
	public Movie findMovieByTitle(String title) throws SpringDataNeo4jRestException {
		LOGGER.info("findMovieByTitle in Service class called for movie title - {}", title);
		return movieRestRepository.findByTitle(title);
    }
	
	public Collection<Movie> findMovieByTitleContaining(String title) throws SpringDataNeo4jRestException {
		LOGGER.info("findMovieByTitleContaining in Service class called for movie title series - {}", title);
		return movieRestRepository.findByTitleContaining(title);
    }
	
	public Set<String> findActorsOfAMovie(@PathVariable(value = "title") String title) throws SpringDataNeo4jRestException {
		return movieRestRepository.findActorsOfAMovie(title);
	}
	
	public Movie saveMovie(MovieRequest movieRequest) throws SpringDataNeo4jRestException {
		Movie movie = new Movie();
		movie.setTitle(movieRequest.getMovieName());
		movie.setReleased(movieRequest.getReleasedYear());
		movieRestRepository.save(movie);
		
		Person person = new Person();
		person.setName(movieRequest.getPersonName());
		personRestRepository.save(person);
		
		Role role = new Role();
		role.setMovie(movie);
		role.setPerson(person);
		Collection<String> roleNames = new HashSet<>();
		roleNames.add(movieRequest.getRoleName());
		role.setRoles(roleNames);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		movie.setRoles(roles);
		movieRestRepository.save(movie);
		LOGGER.info("saveMovie => Movie data saved");
		return movie;
	}
	
	/*@SuppressWarnings("rawtypes")
	private Map<String, Object> toD3Format(Iterator<Map<String, Object>> result) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map("title", row.get("movie"), "label", "movie"));
            int target = i;
            i++;
            for (Object name : (Collection) row.get("cast")) {
                Map<String, Object> actor = map("title", name, "label", "actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source", source, "target", target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }
	
	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }*/

}
