
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

			final List<String> producers = films.stream().map(this::getWinnersProducers(films)).collect(Collectors.toList());

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

	private List<String> getWinnersProducers(List<String> producers) {

		List<String> splitedProducers = new ArrayList<>();

		for (String producer : producers) {
			String partes[] = producer.split(", | and ");

			splitedProducers.addAll(Arrays.asList(partes));
		}

		return splitedProducers;
	}

}