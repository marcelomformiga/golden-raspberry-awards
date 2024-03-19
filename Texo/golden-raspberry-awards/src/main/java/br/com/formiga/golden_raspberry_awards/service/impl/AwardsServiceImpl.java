
package br.com.formiga.golden_raspberry_awards.service.impl;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Service
public class AwardsServiceImpl implements AwardsService {

	private FilmService filmService;

	@Override
	public WinnerPrizeRangeDTO getWinnersByRange() {

		final List<FilmDTO> winners = this.filmService.getAll().stream().filter(FilmDTO::getWinner).collect(Collectors.toList());

		final Map<Integer, String> producersByYear = this.splitProducersIndividually(winners);

		return null;
	}

	/**
	 * This method split the producers individually by year.
	 *
	 * @param winners
	 * @return
	 */
	private static Map<Integer, String> splitProducersIndividually(List<FilmDTO> winners) {

		Map<Integer, String> winnersByYear = new HashMap<>();

		for (final FilmDTO filmDTO : winners) {
			winnersByYear.putAll(AwardsServiceImpl.linkProducerByYear(filmDTO));
		}

		return winnersByYear;
	}

	/**
	 * This method link a Producer by Year.
	 *
	 * @param filmDTO
	 * @return
	 */
	private static Map<Integer, String> linkProducerByYear(FilmDTO filmDTO) {

		Map<Integer, String> winnersByYear = new HashMap<>();

		final String producers[] = filmDTO.getProducers().split(", | and ");

		for (int i = 0; i < producers.length; i ++) {
			winnersByYear.put(filmDTO.getReleaseYear(), producers[i]);
		}

		return winnersByYear;
	}

}