
package br.com.formiga.golden_raspberry_awards.mock;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;

import java.util.*;


public class WinnerPrizeRangeDTOMock {

	public static WinnerPrizeRangeDTO mockWinnerPrizeRangeDTO() {

		final List<FilmDTO> winners = FilmDTOMock.mockListOfWinners();

		final WinnerDTO winnerDTOMin1 = WinnerDTO.builder()
				.producer(winners.get(3).getProducers())
				.interval(1)
				.previousWin(winners.get(2).getReleaseYear())
				.followingWin(winners.get(3).getReleaseYear())
				.build();

		final WinnerDTO winnerDTOMin2 = WinnerDTO.builder()
				.producer(winners.get(4).getProducers())
				.interval(1)
				.previousWin(winners.get(4).getReleaseYear())
				.followingWin(winners.get(5).getReleaseYear())
				.build();

		final WinnerDTO winnerDTOMax = WinnerDTO.builder()
				.producer(winners.get(1).getProducers())
				.interval(5)
				.previousWin(winners.get(1).getReleaseYear())
				.followingWin(winners.get(6).getReleaseYear())
				.build();

		return WinnerPrizeRangeDTO.builder()
				.min(Arrays.asList(winnerDTOMin1, winnerDTOMin2))
				.max(Arrays.asList(winnerDTOMax))
				.build();
	}

}