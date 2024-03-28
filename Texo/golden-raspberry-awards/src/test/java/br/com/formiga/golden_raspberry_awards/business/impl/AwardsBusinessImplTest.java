
package br.com.formiga.golden_raspberry_awards.business.impl;


import br.com.formiga.golden_raspberry_awards.mock.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AwardsBusinessImplTest {

	@InjectMocks
	private AwardsBusinessImpl awardsBusinessMock;

	@Mock
	private FilmService filmServiceMock;


	@Test
	@DisplayName(value = "Test Get Winners By Range - SUCCESS")
	void testGetWinnersByRange_Success() {

		final List<FilmDTO> winners = FilmDTOMock.mockListOfWinners();
		final WinnerPrizeRangeDTO winnerPrizeRangeDTO = WinnerPrizeRangeDTOMock.mockWinnerPrizeRangeDTO();

		Mockito.when(this.filmServiceMock.getAll()).thenReturn(winners);

		final WinnerPrizeRangeDTO response = this.awardsBusinessMock.getWinnersByRange();

		Assertions.assertNotNull(response, "[1] Response cannot be null!");
		Assertions.assertNotNull(response.getMin(), "[2] List cannot be null!");
		Assertions.assertNotNull(response.getMax(), "[3] List cannot be null!");
		Assertions.assertFalse(response.getMin().isEmpty(), "[4] List cannot be empty!");
		Assertions.assertFalse(response.getMax().isEmpty(), "[5] List cannot be empty!");
		Assertions.assertTrue(response.getMin().size() == 2, "[6] List must be size 2!");
		Assertions.assertTrue(response.getMax().size() == 1, "[7] List must be size 1!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(0).getProducer(), response.getMin().get(0).getProducer(), "[8] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(0).getInterval(), response.getMin().get(0).getInterval(), "[9] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(0).getPreviousWin(), response.getMin().get(0).getPreviousWin(), "[10] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(0).getFollowingWin(), response.getMin().get(0).getFollowingWin(), "[11] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(1).getProducer(), response.getMin().get(1).getProducer(), "[12] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(1).getInterval(), response.getMin().get(1).getInterval(), "[13] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(1).getPreviousWin(), response.getMin().get(1).getPreviousWin(), "[14] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMin().get(1).getFollowingWin(), response.getMin().get(1).getFollowingWin(), "[15] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMax().get(0).getProducer(), response.getMax().get(0).getProducer(), "[16] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMax().get(0).getInterval(), response.getMax().get(0).getInterval(), "[17] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMax().get(0).getPreviousWin(), response.getMax().get(0).getPreviousWin(), "[18] Objects must be equals!");
		Assertions.assertEquals(winnerPrizeRangeDTO.getMax().get(0).getFollowingWin(), response.getMax().get(0).getFollowingWin(), "[19] Objects must be equals!");

		Mockito.verify(this.filmServiceMock, Mockito.times(1)).getAll();
	}

}