
package br.com.formiga.golden_raspberry_awards.business.impl;


import br.com.formiga.golden_raspberry_awards.business.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import com.google.common.collect.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Service
public class AwardsBusinessImpl implements AwardsBusiness {

	private static final Logger LOGGER = LoggerFactory.getLogger(AwardsBusinessImpl.class);

	@Autowired
	private FilmService filmService;


	@Override
	public WinnerPrizeRangeDTO getWinnersByRange() {

		LOGGER.debug("Getting all winners.");

		final List<FilmDTO> winners = this.filmService.getAll().stream().filter(FilmDTO::getWinner).collect(Collectors.toList());

		Multimap<Integer, String> producersByYear = AwardsBusinessImpl.splitProducersIndividually(winners);

		List<WinnerDTO> min = new ArrayList<>();
		List<WinnerDTO> max = new ArrayList<>();

		Multimap<Integer, String> producersToCompare = producersByYear;

		for (Map.Entry<Integer, String> producer: producersByYear.entries()) {

			Integer yearToCompare = producer.getKey();

			WinnerDTO winnerDTO = new WinnerDTO();

			for (Map.Entry<Integer, String> producerToCompare : producersToCompare.entries()) {

				LOGGER.debug("Reading all winners and comparing to the first one.");

				if ((producer.getValue().equals(producerToCompare.getValue())) && (producer.getKey() < producerToCompare.getKey())) {

					winnerDTO.setProducer(producer.getValue());
					winnerDTO.setInterval(producerToCompare.getKey() - yearToCompare);
					winnerDTO.setPreviousWin(yearToCompare);
					winnerDTO.setFollowingWin(producerToCompare.getKey());

					if (min.isEmpty()) {

						min.add(winnerDTO);
						max.add(winnerDTO);

						continue;
					}

					if (min.get(0).getInterval() > winnerDTO.getInterval()) {
						min = new ArrayList<>();
						min.add(winnerDTO);
					} else if (min.get(0).getInterval().equals(winnerDTO.getInterval())) {
						min.add(winnerDTO);
					}

					if (max.get(0).getInterval() < winnerDTO.getInterval()) {
						max = new ArrayList<>();
						max.add(winnerDTO);
					} else if (max.get(0).getInterval().equals(winnerDTO.getInterval())) {
						max.add(winnerDTO);
					}

					yearToCompare = producerToCompare.getKey();
				}
			}

		}

		return WinnerPrizeRangeDTO.builder()
				.min(min)
				.max(max)
				.build();
	}

	/**
	 * This method split the producers individually by year.
	 *
	 * @param winners
	 * @return
	 */
	private static ListMultimap<Integer, String> splitProducersIndividually(List<FilmDTO> winners) {

		LOGGER.debug("Splitting the producers individually by year where there are more than one winner.");

		ListMultimap<Integer, String> winnersByYear = ArrayListMultimap.create();

		for (final FilmDTO filmDTO : winners) {
			winnersByYear.putAll(AwardsBusinessImpl.linkProducerByYear(filmDTO));
		}

		return winnersByYear;
	}

	/**
	 * This method link a Producer by Year.
	 *
	 * @param filmDTO
	 * @return
	 */
	private static ListMultimap<Integer, String> linkProducerByYear(FilmDTO filmDTO) {

		ListMultimap<Integer, String> multimap = ArrayListMultimap.create();

		LOGGER.debug("Excluding specials characters and the conjunction.");

		final String producers[] = filmDTO.getProducers().split(", | and ");

		for (int i = 0; i < producers.length; i ++) {
			multimap.put(filmDTO.getReleaseYear(), producers[i]);
		}

		return multimap;
	}

}