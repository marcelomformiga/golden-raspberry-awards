
package br.com.formiga.golden_raspberry_awards.rest.controller;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/awards")
public class AwardController {

	@GetMapping(path = "/winners")
	public ResponseEntity<String> getWinnersByRange() {
		return ResponseEntity.ok("Winners!");
	}

}