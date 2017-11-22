package com.example.actor_movie_manytomany.entities;

import com.example.actor_movie_manytomany.entities.Actor;

import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long>
{
	Iterable<Actor> findAllByRealnameContainingIgnoreCase(String s);
	Iterable<Actor> findAllByMoviesNotContaining(Movie thisMovie);

}
