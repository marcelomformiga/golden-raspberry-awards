
package br.com.formiga.golden_raspberry_awards.service;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;

import java.util.*;


public interface FilmService {

	FilmDTO save(final FilmDTO filmDTO);

	List<FilmDTO> getAll();

}