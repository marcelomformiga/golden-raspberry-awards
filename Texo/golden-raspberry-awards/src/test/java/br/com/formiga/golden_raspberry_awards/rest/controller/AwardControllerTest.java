
package br.com.formiga.golden_raspberry_awards.rest.controller;


import br.com.formiga.golden_raspberry_awards.business.*;
import br.com.formiga.golden_raspberry_awards.mock.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AwardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AwardsBusiness awardsServiceMock;


	@Test
	@DisplayName(value = "Get Winners By Range - SUCCESS")
	void testGetWinnersByRange() throws Exception {

		final WinnerPrizeRangeDTO winnerPrizeRangeDTO = WinnerPrizeRangeDTOMock.mockWinnerPrizeRangeDTO();

		Mockito.when(this.awardsServiceMock.getWinnersByRange()).thenReturn(winnerPrizeRangeDTO);

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

		Mockito.verify(this.awardsServiceMock, Mockito.times(1)).getWinnersByRange();
	}

}