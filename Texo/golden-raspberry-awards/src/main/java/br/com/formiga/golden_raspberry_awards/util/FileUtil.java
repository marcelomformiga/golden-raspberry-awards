
package br.com.formiga.golden_raspberry_awards.util;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

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
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			List<FilmDTO> films = reader.lines()
					.skip(1)
					.map(this::getLine)
					.collect(Collectors.toList());

			for (FilmDTO filmDTO : films) {
				this.filmService.save(filmDTO);
			}

			reader.close();

		} catch (Exception e) {
			System.err.println("Erro ao ler arquivo CSV: " + e.getMessage());
		}

	}

	private FilmDTO getLine(final String line) {

		final String fields[] = line.split(";");

		return FilmDTO.builder()
				.releaseYear(Integer.parseInt(fields[0]))
				.title(fields[1])
				.studio(fields[2])
				.producer(fields[3])
				.build();
	}

}