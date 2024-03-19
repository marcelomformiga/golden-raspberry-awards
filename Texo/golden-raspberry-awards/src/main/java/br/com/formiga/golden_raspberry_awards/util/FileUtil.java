
package br.com.formiga.golden_raspberry_awards.util;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;


@Component
public class FileUtil {

	@Autowired
	private FilmService filmService;


	@PostConstruct
	public void readFile() {

		try {

			InputStream inputStream = getClass().getResourceAsStream("/movies-list.csv");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			List<FilmDTO> films = bufferedReader.lines()
					.skip(1)
					.map(this::getLine)
					.collect(Collectors.toList());

			films.stream().forEach(filmDTO -> this.filmService.save(filmDTO));

			bufferedReader.close();

//			this.getFilmesAtivos(films).forEach(System.out::println);

			for (FilmDTO filmDTO : this.getFilmesAtivos(films)) {
				System.out.println(filmDTO.getReleaseYear().toString().concat(" - ").concat(filmDTO.getTitle().concat( " - ").concat(filmDTO.getProducers().concat(" - ").concat(filmDTO.getWinner().toString()))));
			}

		} catch (Exception e) {
			System.err.println("Failed to read file CSV: " + e.getMessage());
		}

	}

	private FilmDTO getLine(final String line) {

		final String fields[] = line.split(";");

		final Boolean winner = ((fields.length > 4) && (StringUtils.hasLength(fields[4])) && (fields[4].equals("yes"))) ? Boolean.TRUE : Boolean.FALSE;

		FilmDTO filmDTO = FilmDTO.builder()
				.releaseYear(Integer.parseInt(fields[0]))
				.title(fields[1])
				.studio(fields[2])
				.producers(fields[3])
				.winner(winner)
				.build();

		return filmDTO;
	}

	private List<FilmDTO> getFilmesAtivos(final List<FilmDTO> filmes) {
		return filmes.stream().filter(FilmDTO::getWinner).collect(Collectors.toList());
	}

}