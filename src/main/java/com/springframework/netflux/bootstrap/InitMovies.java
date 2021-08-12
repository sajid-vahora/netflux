package com.springframework.netflux.bootstrap;

import com.springframework.netflux.domain.Movie;
import com.springframework.netflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class InitMovies implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        movieRepository.deleteAll()
                .thenMany(Flux.just("RHTDM", "MPK", "Die Hard 1", "Die Hard 2", "MI 1", "MI 2", "MI 3", "MI 4",
                        "Iron man 1", "Iron Man 2", "Iron Man 3")
                        .map(Movie::new)
                        .flatMap(movieRepository::save))
                .subscribe(null, null, () ->
                        movieRepository.findAll().subscribe(System.out::println));

    }
}
