
package br.com.formiga.golden_raspberry_awards.rest.controller;


import br.com.formiga.golden_raspberry_awards.business.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/awards")
public class AwardController {

	@Autowired
	private AwardsBusiness awardsService;


	@GetMapping(path = "/winners-range", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WinnerPrizeRangeDTO> getWinnersByRange() {

		return ResponseEntity.ok(this.awardsService.getWinnersByRange());
	}

}