
package br.com.formiga.golden_raspberry_awards.service.impl;


import br.com.formiga.golden_raspberry_awards.business.impl.*;
import br.com.formiga.golden_raspberry_awards.mock.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;


@ExtendWith(MockitoExtension.class)
class AwardsServiceImplTest {

	@InjectMocks
	private AwardsBusinessImpl awardsServiceMock;

	@Mock
	private FilmService filmServiceMock;


	@Test
	@DisplayName(value = "Get Winners By Range - SUCCESS!")
	void testGetWinnersByRange() {

		final List<FilmDTO> winners = FilmDTOMock.mockListOfWinners();

		Mockito.when(this.filmServiceMock.getAll()).thenReturn(winners);

		final WinnerPrizeRangeDTO response = this.awardsServiceMock.getWinnersByRange();

		Assertions.assertNotNull(response, "[1] Response cannot be null!");
		Assertions.assertNotNull(response.getMin(), "[2] List cannot be null!");
		Assertions.assertNotNull(response.getMax(), "[3] List cannot be null!");
		Assertions.assertFalse((response.getMin().isEmpty() || (response.getMax().isEmpty())), "[4] Lists cannot be empty!");
		Assertions.assertTrue(response.getMin().size() == 2, "[5] List must has size 2!");
		Assertions.assertTrue(response.getMax().size() == 1, "[6] List must has size 1!");
		Assertions.assertEquals(winners.get(3).getProducers(), response.getMin().get(0).getProducer(), "[7] Objects must be equals!");
		Assertions.assertEquals(1, response.getMin().get(0).getInterval(), "[8] Objects must be equals!");
		Assertions.assertEquals(winners.get(2).getReleaseYear(), response.getMin().get(0).getPreviousWin(), "[9] Objects must be equals!");
		Assertions.assertEquals(winners.get(3).getReleaseYear(), response.getMin().get(0).getFollowingWin(), "[10] Objects must be equals!");
		Assertions.assertEquals(winners.get(4).getProducers(), response.getMin().get(1).getProducer(), "[11] Objects must be equals!");
		Assertions.assertEquals(1, response.getMin().get(1).getInterval(), "[12] Objects must be equals!");
		Assertions.assertEquals(winners.get(4).getReleaseYear(), response.getMin().get(1).getPreviousWin(), "[13] Objects must be equals!");
		Assertions.assertEquals(winners.get(5).getReleaseYear(), response.getMin().get(1).getFollowingWin(), "[14] Objects must be equals!");
		Assertions.assertEquals(winners.get(1).getProducers(), response.getMax().get(0).getProducer(), "[15] Objects must be equals!");
		Assertions.assertEquals(5, response.getMax().get(0).getInterval(), "[16] Objects must be equals!");
		Assertions.assertEquals(winners.get(1).getReleaseYear(), response.getMax().get(0).getPreviousWin(), "[17] Objects must be equals!");
		Assertions.assertEquals(winners.get(6).getReleaseYear(), response.getMax().get(0).getFollowingWin(), "[18] Objects must be equals!");

		Mockito.verify(this.filmServiceMock, Mockito.times(1)).getAll();
	}

}