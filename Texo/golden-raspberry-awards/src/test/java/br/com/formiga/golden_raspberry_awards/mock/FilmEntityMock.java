
package br.com.formiga.golden_raspberry_awards.mock;


import br.com.formiga.golden_raspberry_awards.domain.model.entity.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;


public class FilmEntityMock {

	public static FilmEntity mockFilmEntity() {

		final FilmDTO filmDTO = FilmDTOMock.mockFilmDTO();

		return FilmEntity.builder()
				.id(1L)
				.releaseYear(filmDTO.getReleaseYear())
				.title(filmDTO.getTitle())
				.studio(filmDTO.getStudio())
				.producers(filmDTO.getProducers())
				.winner(filmDTO.getWinner())
				.build();
	}

	public static FilmEntity mockFilmEntityWinner() {

		final FilmDTO filmDTO = FilmDTOMock.mockFilmDTOWinner();

		return FilmEntity.builder()
				.id(2L)
				.releaseYear(filmDTO.getReleaseYear())
				.title(filmDTO.getTitle())
				.studio(filmDTO.getStudio())
				.producers(filmDTO.getProducers())
				.winner(filmDTO.getWinner())
				.build();
	}

}