
package br.com.formiga.golden_raspberry_awards.service.impl;


import br.com.formiga.golden_raspberry_awards.domain.model.entity.*;
import br.com.formiga.golden_raspberry_awards.domain.repository.*;
import br.com.formiga.golden_raspberry_awards.mock.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;


@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {

	@InjectMocks
	private FilmServiceImpl filmServiceMock;

	@Mock
	private FilmRepository filmRepositoryMock;


	@Test
	@DisplayName(value = "Test Save - SUCCESS")
	void testSave_Success() {

		final FilmDTO filmDTO = FilmDTOMock.mockFilmDTO();

		FilmEntity filmEntity = FilmEntityMock.mockFilmEntity();
		filmEntity.setId(null);

		final FilmEntity savedFilm = FilmEntityMock.mockFilmEntity();

		Mockito.when(this.filmRepositoryMock.save(filmEntity)).thenReturn(savedFilm);

		final FilmDTO response = this.filmServiceMock.save(filmDTO);

		Assertions.assertNotNull(response, "[1] Response cannot be null!");
		Assertions.assertEquals(savedFilm.getReleaseYear(), response.getReleaseYear(), "[2] Objects must be equals!");
		Assertions.assertEquals(savedFilm.getTitle(), response.getTitle(), "[3] Objects must be equals!");
		Assertions.assertEquals(savedFilm.getStudio(), response.getStudio(), "[4] Objects must be equals!");
		Assertions.assertEquals(savedFilm.getProducer(), response.getProducer(), "[5] Objects must be equals!");

		Mockito.verify(this.filmRepositoryMock, Mockito.times(1)).save(filmEntity);
	}

}