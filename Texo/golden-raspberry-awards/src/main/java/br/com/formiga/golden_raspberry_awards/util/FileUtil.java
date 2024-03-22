
package br.com.formiga.golden_raspberry_awards.util;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import jakarta.annotation.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;


@Component
public class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	@Autowired
	private FilmService filmService;


	@PostConstruct
	public void readFile() {

		try {

			LOGGER.debug("Reading file.");

			final InputStream inputStream = super.getClass().getResourceAsStream("/movies-list.csv");
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			final List<FilmDTO> films = bufferedReader.lines()
					.skip(1)
					.map(this::getLine)
					.collect(Collectors.toList());

			films.stream().forEach(filmDTO -> this.filmService.save(filmDTO));

			bufferedReader.close();

		} catch (final Exception exception) {
			LOGGER.error("Failed to read file CSV: " + exception.getMessage());
		}
	}

	private FilmDTO getLine(final String line) {

		LOGGER.debug("Read a line and then split the fields.");
		final String fields[] = line.split(";");

		LOGGER.debug("Validate either it is a winner or not.");
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

}