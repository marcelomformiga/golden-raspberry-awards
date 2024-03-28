
package br.com.formiga.golden_raspberry_awards.service.impl;


import br.com.formiga.golden_raspberry_awards.domain.model.entity.*;
import br.com.formiga.golden_raspberry_awards.domain.repository.*;
import br.com.formiga.golden_raspberry_awards.mock.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;


@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {

	@InjectMocks
	private FilmServiceImpl filmServiceMock;

	@Mock
	private FilmRepository filmRepositoryMock;


	@Test
	@DisplayName(value = "Test Save - SUCCESS")
	void testSave_Success() {

		final FilmDTO filmDTO = FilmDTOMock.mockFilmDTOWinner();

		FilmEntity filmEntity = FilmEntityMock.mockFilmEntityWinner();
		filmEntity.setId(null);

		final FilmEntity savedFilm = FilmEntityMock.mockFilmEntityWinner();

		Mockito.when(this.filmRepositoryMock.save(filmEntity)).thenReturn(savedFilm);

		final FilmDTO response = this.filmServiceMock.save(filmDTO);

		Assertions.assertNotNull(response, "[1] Response cannot be null!");
		Assertions.assertEquals(savedFilm.getReleaseYear(), response.getReleaseYear(), "[2] Objects must be equals!");
		Assertions.assertEquals(savedFilm.getTitle(), response.getTitle(), "[3] Objects must be equals!");
		Assertions.assertEquals(savedFilm.getStudio(), response.getStudio(), "[4] Objects must be equals!");
		Assertions.assertEquals(savedFilm.getProducers(), response.getProducers(), "[5] Objects must be equals!");
		Assertions.assertEquals(savedFilm.getWinner(), response.getWinner(), "[6] Objects must be equals!");

		Mockito.verify(this.filmRepositoryMock, Mockito.times(1)).save(filmEntity);
	}

	@Test
	@DisplayName(value = "Test Get All - SUCCESS")
	void testGetAll_Success() {

		final List<FilmEntity> entities = Arrays.asList(FilmEntityMock.mockFilmEntity(), FilmEntityMock.mockFilmEntityWinner());

		final FilmDTO filmDTO = FilmDTOMock.mockFilmDTO();
		final FilmDTO filmDTOWinner = FilmDTOMock.mockFilmDTOWinner();

		Mockito.when(this.filmRepositoryMock.findAll()).thenReturn(entities);

		final List<FilmDTO> response = this.filmServiceMock.getAll();

		Assertions.assertNotNull(response, "[1] Response cannot be null!");
		Assertions.assertFalse(response.isEmpty(), "[2] List cannot be empty!");
		Assertions.assertTrue(response.size() == 2, "[3] List must be size 2!");
		Assertions.assertEquals(filmDTO.getTitle(), response.get(0).getTitle(), "[4] Objects must be equals!");
		Assertions.assertEquals(filmDTO.getReleaseYear(), response.get(0).getReleaseYear(), "[5] Objects must be equals!");
		Assertions.assertEquals(filmDTO.getStudio(), response.get(0).getStudio(), "[6] Objects must be equals!");
		Assertions.assertEquals(filmDTO.getProducers(), response.get(0).getProducers(), "[7] Objects must be equals!");
		Assertions.assertEquals(filmDTO.getWinner(), response.get(0).getWinner(), "[8] Objects must be equals!");
		Assertions.assertEquals(filmDTOWinner.getTitle(), response.get(1).getTitle(), "[9] Objects must be equals!");
		Assertions.assertEquals(filmDTOWinner.getReleaseYear(), response.get(1).getReleaseYear(), "[10] Objects must be equals!");
		Assertions.assertEquals(filmDTOWinner.getStudio(), response.get(1).getStudio(), "[11] Objects must be equals!");
		Assertions.assertEquals(filmDTOWinner.getProducers(), response.get(1).getProducers(), "[12] Objects must be equals!");
		Assertions.assertEquals(filmDTOWinner.getWinner(), response.get(1).getWinner(), "[13] Objects must be equals!");

		Mockito.verify(this.filmRepositoryMock, Mockito.times(1)).findAll();
	}

}