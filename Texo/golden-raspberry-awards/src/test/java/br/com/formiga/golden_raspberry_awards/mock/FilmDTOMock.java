
package br.com.formiga.golden_raspberry_awards.mock;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;


public class FilmDTOMock {

	public static FilmDTO mockFilmDTO() {

		return FilmDTO.builder()
				.releaseYear(1980)
				.title("The Formula")
				.studio("MGM")
				.producer("United Artists")
				.build();
	}

}