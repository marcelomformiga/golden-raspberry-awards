
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
				.producer(filmDTO.getProducer())
				.build();
	}

}