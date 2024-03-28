
package br.com.formiga.golden_raspberry_awards.rest.controller;


import br.com.formiga.golden_raspberry_awards.business.*;
import br.com.formiga.golden_raspberry_awards.business.impl.*;
import br.com.formiga.golden_raspberry_awards.mock.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.util.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import java.util.*;
import java.util.stream.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class AwardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AwardsBusiness awardsBusinessMock;

	@Autowired
	private FileUtil fileUtil;


	@Test
	@DisplayName(value = "Get Winners By Range - SUCCESS")
	void testGetWinnersByRange() throws Exception {

		final WinnerPrizeRangeDTO winnerPrizeRangeDTO = WinnerPrizeRangeDTOMock.mockWinnerPrizeRangeDTO();

		Mockito.when(this.awardsBusinessMock.getWinnersByRange()).thenReturn(winnerPrizeRangeDTO);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/awards/winners-range")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.min").isNotEmpty())
				.andExpect(jsonPath("$.min[0].producer").value(winnerPrizeRangeDTO.getMin().get(0).getProducer()))
				.andExpect(jsonPath("$.min[0].interval").value(winnerPrizeRangeDTO.getMin().get(0).getInterval()))
				.andExpect(jsonPath("$.min[0].previousWin").value(winnerPrizeRangeDTO.getMin().get(0).getPreviousWin()))
				.andExpect(jsonPath("$.min[0].followingWin").value(winnerPrizeRangeDTO.getMin().get(0).getFollowingWin()))
				.andExpect(jsonPath("$.min[1].producer").value(winnerPrizeRangeDTO.getMin().get(1).getProducer()))
				.andExpect(jsonPath("$.min[1].interval").value(winnerPrizeRangeDTO.getMin().get(1).getInterval()))
				.andExpect(jsonPath("$.min[1].previousWin").value(winnerPrizeRangeDTO.getMin().get(1).getPreviousWin()))
				.andExpect(jsonPath("$.min[1].followingWin").value(winnerPrizeRangeDTO.getMin().get(1).getFollowingWin()))
				.andExpect(jsonPath("$.max").isNotEmpty())
				.andExpect(jsonPath("$.max[0].producer").value(winnerPrizeRangeDTO.getMax().get(0).getProducer()))
				.andExpect(jsonPath("$.max[0].interval").value(winnerPrizeRangeDTO.getMax().get(0).getInterval()))
				.andExpect(jsonPath("$.max[0].previousWin").value(winnerPrizeRangeDTO.getMax().get(0).getPreviousWin()))
				.andExpect(jsonPath("$.max[0].followingWin").value(winnerPrizeRangeDTO.getMax().get(0).getFollowingWin()));

		Mockito.verify(this.awardsBusinessMock, Mockito.times(1)).getWinnersByRange();
	}

	@Test
	@DisplayName(value = "Get Winners By Range - Validate File - SUCCESS")
	void testGetWinnersByRange_ValidateFile() throws Exception {

		final List<FilmDTO> films = this.fileUtil.readFile();
		final List<FilmDTO> winners = films.stream().filter(FilmDTO::getWinner).collect(Collectors.toList());

		final WinnerPrizeRangeDTO winnerPrizeRangeDTO = AwardsBusinessImpl.mountWinnersList(winners);

		Mockito.when(this.awardsBusinessMock.getWinnersByRange()).thenReturn(winnerPrizeRangeDTO);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/awards/winners-range")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.min").isNotEmpty())
				.andExpect(jsonPath("$.min[0].producer").value(winnerPrizeRangeDTO.getMin().get(0).getProducer()))
				.andExpect(jsonPath("$.min[0].interval").value(winnerPrizeRangeDTO.getMin().get(0).getInterval()))
				.andExpect(jsonPath("$.min[0].previousWin").value(winnerPrizeRangeDTO.getMin().get(0).getPreviousWin()))
				.andExpect(jsonPath("$.min[0].followingWin").value(winnerPrizeRangeDTO.getMin().get(0).getFollowingWin()))
				.andExpect(jsonPath("$.max").isNotEmpty())
				.andExpect(jsonPath("$.max[0].producer").value(winnerPrizeRangeDTO.getMax().get(0).getProducer()))
				.andExpect(jsonPath("$.max[0].interval").value(winnerPrizeRangeDTO.getMax().get(0).getInterval()))
				.andExpect(jsonPath("$.max[0].previousWin").value(winnerPrizeRangeDTO.getMax().get(0).getPreviousWin()))
				.andExpect(jsonPath("$.max[0].followingWin").value(winnerPrizeRangeDTO.getMax().get(0).getFollowingWin()));

		Assertions.assertTrue(winnerPrizeRangeDTO.getMin().size() == 1, "[1] Min must be size 1!");
		Assertions.assertTrue(winnerPrizeRangeDTO.getMax().size() == 1, "[2] Max must be size 1!");

		Assertions.assertEquals("Joel Silver", winnerPrizeRangeDTO.getMin().get(0).getProducer(), "[3] Objects must be equals!");
		Assertions.assertEquals(1, winnerPrizeRangeDTO.getMin().get(0).getInterval(), "[4] Objects must be equals!");
		Assertions.assertEquals(1990, winnerPrizeRangeDTO.getMin().get(0).getPreviousWin(), "[5] Objects must be equals!");
		Assertions.assertEquals(1991, winnerPrizeRangeDTO.getMin().get(0).getFollowingWin(), "[6] Objects must be equals!");

		Assertions.assertEquals("Matthew Vaughn", winnerPrizeRangeDTO.getMax().get(0).getProducer(), "[7] Objects must be equals!");
		Assertions.assertEquals(13, winnerPrizeRangeDTO.getMax().get(0).getInterval(), "[8] Objects must be equals!");
		Assertions.assertEquals(2002, winnerPrizeRangeDTO.getMax().get(0).getPreviousWin(), "[9] Objects must be equals!");
		Assertions.assertEquals(2015, winnerPrizeRangeDTO.getMax().get(0).getFollowingWin(), "[10] Objects must be equals!");

		Mockito.verify(this.awardsBusinessMock, Mockito.times(1)).getWinnersByRange();
	}

}