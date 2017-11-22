package com.example.actor_movie_manytomany.entities;

import com.example.actor_movie_manytomany.entities.Movie;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String realname;
	private String headshot;

//	 ACTORS ARE MAPPED TO MOVIES, SINCE ACTORS ARE CASTS INSIDE MOVIES AND MOVIE IS OWNER OF ACTORS
//		WE ARE CREATING MANY TO MANY RELATION BETWEEN THE TWO. ONE ACTOR CAN MAKE MANY MOVIES AN IN ONE MOVIE THERE CAN BE MANY ACTORS. SO WHEN WE JOIN THE TWO MANY TO MANY RELATION WE ARE CREATING NEW TABLE CALLED CAST.
//      WE WILL CREATE AN EMPTY CONSTRUCTOR TO ADD SET OF MOVIES


	@ManyToMany(mappedBy = "cast")
	private Set<Movie> movies;


	public Actor()
	{
		movies=new HashSet<Movie>();
	}

	public void addMovie(Movie m)
	{
		movies.add(m);
	}


//	GETTER AND SETTERS
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRealname()
	{
		return realname;
	}

	public void setRealname(String realname)
	{
		this.realname = realname;
	}

	public String getHeadshot()
	{
		return headshot;
	}

	public void setHeadshot(String headshot)
	{
		this.headshot = headshot;
	}

	public Set<Movie> getMovies()
	{
		return movies;
	}

	public void setMovies(Set<Movie> movies)
	{
		this.movies = movies;
	}
}
