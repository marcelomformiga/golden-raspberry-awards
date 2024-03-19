
package br.com.formiga.golden_raspberry_awards.mock;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;


public class FilmDTOMock {

	public static FilmDTO mockFilmDTO() {

		return FilmDTO.builder()
				.releaseYear(1980)
				.title("The Formula")
				.studio("MGM")
				.producers("United Artists")
				.winner(Boolean.FALSE)
				.build();
	}

	public static FilmDTO mockFilmDTOWinner() {

		return FilmDTO.builder()
				.releaseYear(1981)
				.title("Mommie Dearest")
				.studio("Paramount Pictures")
				.producers("Frank Yablans")
				.winner(Boolean.TRUE)
				.build();
	}

}