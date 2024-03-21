
package br.com.formiga.golden_raspberry_awards.mock;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;

import java.util.*;


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

	public static List<FilmDTO> mockListOfWinners() {

		final FilmDTO winner2000 = FilmDTO.builder()
				.releaseYear(2000)
				.title("Mommie Dearest")
				.studio("Paramount Pictures")
				.producers("Frank Yablans")
				.winner(Boolean.TRUE)
				.build();

		final FilmDTO winner2001 = FilmDTO.builder()
				.releaseYear(2001)
				.title("Inchon")
				.studio("MGM")
				.producers("Mitsuharu Ishii")
				.winner(Boolean.TRUE)
				.build();

		final FilmDTO winner2002 = FilmDTO.builder()
				.releaseYear(2002)
				.title("The Lonely Lady")
				.studio("MGM")
				.producers("Yoram Globus and Menahem Golan")
				.winner(Boolean.TRUE)
				.build();

		final FilmDTO winner2003 = FilmDTO.builder()
				.releaseYear(2003)
				.title("The Lonely Lady 2")
				.studio("MGM")
				.producers("Yoram Globus")
				.winner(Boolean.TRUE)
				.build();

		final FilmDTO winner2004 = FilmDTO.builder()
				.releaseYear(2004)
				.title("Bolero")
				.studio("Cannon Films")
				.producers("Bo Derek")
				.winner(Boolean.TRUE)
				.build();

		final FilmDTO winner2005 = FilmDTO.builder()
				.releaseYear(2005)
				.title("Bolero 2")
				.studio("Cannon Films")
				.producers("Bo Derek")
				.winner(Boolean.TRUE)
				.build();

		final FilmDTO winner2006 = FilmDTO.builder()
				.releaseYear(2006)
				.title("Inchon 2")
				.studio("MGM")
				.producers("Mitsuharu Ishii")
				.winner(Boolean.TRUE)
				.build();

		return Arrays.asList(winner2000, winner2001, winner2002, winner2003, winner2004, winner2005, winner2006);
	}

}