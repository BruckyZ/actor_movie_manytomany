package com.example.actor_movie_manytomany.Controllers;

import com.cloudinary.utils.ObjectUtils;

import com.example.actor_movie_manytomany.CloudinaryConfig;
import com.example.actor_movie_manytomany.entities.Actor;
import com.example.actor_movie_manytomany.entities.Actor;
import com.example.actor_movie_manytomany.entities.ActorRepository;
import com.example.actor_movie_manytomany.entities.Movie;
import com.example.actor_movie_manytomany.entities.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.io.IOException;

@Controller
public class MainController
{

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	CloudinaryConfig clodc;

	@RequestMapping("/")
	public String showIndex(Model model)
	{
		model.addAttribute("gotmovies", movieRepository.count());
		model.addAttribute("gotmovies", actorRepository.count());
		model.addAttribute("gotmovies", actorRepository.findAll());
		model.addAttribute("gotmovies", movieRepository.findAll());
		model.addAttribute("title", "Movie Database");
		return "index";
	}
	@GetMapping("/addmovie")
	public String addMovie(Model model)
	{
		Movie movie=new Movie();
		model.addAttribute("movie", movie);
		return "addmovie";
	}

	@PostMapping("/addmovie")
			public String saveMovie(@ModelAttribute("movie") Movie movie)
	{
		movieRepository.save(movie);
		return "redirect:/";
	}
	@GetMapping("/addactor")
			public String saveActor(@ModelAttribute("actor") Actor actor, MultipartHttpServletRequest request)
	{
		MultipartFile f= request.getFile("file");
		if(f.isEmpty())
		{
			return "redirect:/addactor";
		}
		try
		{

			Map uploadResult = clodc.upload(f.getBytes(), ObjectUtils.asMap("resourcetype","auto"));
			String uploadURL=(String)uploadResult.get("url");
			String uploadedName=(String)uploadResult.get("public_id");
			String transformedImage=clodc.createURL(uploadedName);

			System.out.println(transformedImage);
			System.out.println("Uploaded:"+uploadURL);
			System.out.println("Name:"+uploadedName);

			actor.setHeadshot(transformedImage);
			actorRepository.save(actor);

		}
		catch(IOException e)
		{
			e.printStackTrace();
			return "redirect:/addactor";
		}
		return "redirect:/";
		}

		@GetMapping("/addactorstomovie/{id}")
			public String addActor(@PathVariable("id")long movieID, Model model)
		{
			Movie thisMovie=movieRepository.findOne(new Long(movieID));
			Iterable actorsInMovie = thisMovie.getCast();

			model.addAttribute("mov", thisMovie);
			model.addAttribute("actorlist", actorRepository.findAllByMoviesNotContaining(thisMovie));
			return "movieaddactor";
		}
		@GetMapping("/addmoviestoactor/{id}")
				public String addMovie(@PathVariable("id") long actorID, Model model)
		{
			model.addAttribute("actor", actorRepository.findOne(new Long(actorID)));
			model.addAttribute("movieList", movieRepository.findAll());
			return "movieaddactor";
		}

		@PostMapping("/addmoviestoactor/{movid}")
		public String addMoviestoActor(@RequestParam("actors") String actorID, @PathVariable("movid") long movieID,
		                               @ModelAttribute("anActor") Actor a, Model model)
		{
		Movie m= movieRepository.findOne(new Long(movieID));
		m.addActors(actorRepository.findOne(new Long(actorID)));
		movieRepository.save(m);
		model.addAttribute("actorList", actorRepository.findAll());
		model.addAttribute("movieList", movieRepository.findAll());
		return "redirect:/";
}
		@RequestMapping("/search")
		public String SearchResult()
		{
			Iterable<Actor>actors=actorRepository.findAllByRealnameContainingIgnoreCase("Sandra");

				for(Actor a: actors)
				{
				System.out.println(a.getName());
				}

		for(Movie m:movieRepository.findAllByCastIn(actors))
		{
			System.out.println(m.getTitle());
		}
		return "redirect:/";
		}



}
