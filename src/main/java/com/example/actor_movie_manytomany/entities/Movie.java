package com.example.actor_movie_manytomany.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;
	private String year;
	private String description;


	//	 ACTORS ARE MAPPED TO MOVIES, SINCE ACTORS ARE CASTS INSIDE MOVIES AND MOVIE IS OWNER OF ACTORS
//		WE ARE CREATING MANY TO MANY RELATION BETWEEN THE TWO. ONE ACTOR CAN MAKE MANY MOVIES AN IN ONE MOVIE THERE CAN BE MANY ACTORS. SO WHEN WE JOIN THE TWO MANY TO MANY RELATION WE ARE CREATING NEW TABLE CALLED CAST.
//      WE WILL CREATE AN EMPTY CONSTRUCTOR TO ADD SET OF MOVIES
	public Movie()
	{
		this.cast=new HashSet<Actor>();
	}

	@ManyToMany()
	private Set<Actor> cast;

	public void addActors(Actor a)
	{
		this.cast.add(a);
	}


//		GETTER AND SETTERS
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Set<Actor> getCast()
	{
		return cast;
	}

	public void setCast(Set<Actor> cast)
	{
		this.cast = cast;
	}
}