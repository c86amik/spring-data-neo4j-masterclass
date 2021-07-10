package com.springcavaj.springneo4j.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.springcavaj.springneo4j.model.Person;

/**
 * @author springcavaj
 *
 */
@Repository
public interface SpringDataNeo4jPersonRestRepository extends Neo4jRepository<Person, Long> {
	
	public List<Person> findByName(String name);

}
