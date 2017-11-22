package com.example.actor_movie_manytomany.entities;

import com.example.actor_movie_manytomany.entities.Actor;
import com.example.actor_movie_manytomany.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long>
{

	Iterable<Movie> findAllByCastIn(Iterable<Actor> actors);
}
