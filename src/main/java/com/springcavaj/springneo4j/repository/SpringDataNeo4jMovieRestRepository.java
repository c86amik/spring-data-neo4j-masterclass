/**
 * 
 */
package com.springcavaj.springneo4j.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springcavaj.springneo4j.model.Movie;

/**
 * @author springcavaj
 *
 */
@Repository
public interface SpringDataNeo4jMovieRestRepository extends Neo4jRepository<Movie, Long> {
	
	public Movie findByTitle(@Param("title") String title);

    @Query("MATCH (m:Movie) WHERE m.title =~ ('(?i).*'+$title+'.*') RETURN m")
    public Collection<Movie> findByTitleContaining(@Param("title") String title);

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) WHERE m.title =~ ('(?i).*'+$title+'.*') RETURN a.name")
    public Set<String> findActorsOfAMovie(@Param("title") String title);

}
