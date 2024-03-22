
package br.com.formiga.golden_raspberry_awards.util;


import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.*;

import java.io.*;
import java.nio.file.*;


@ExtendWith(SpringExtension.class)
class FileUtilTest {

	@InjectMocks
	private FileUtil fileUtilMock;

	@Mock
	private FilmService filmServiceMock;


	@Test
	@DisplayName(value = "Read File - SUCCESS")
	void testReadFile_Success() throws Exception {

		Mockito.when(this.filmServiceMock.save(ArgumentMatchers.any(FilmDTO.class))).thenReturn(ArgumentMatchers.any(FilmDTO.class));

		this.fileUtilMock.readFile();

		Mockito.verify(this.filmServiceMock, Mockito.times(206)).save(ArgumentMatchers.any(FilmDTO.class));
	}

	@Test
	@DisplayName(value = "This test compares the main file to original file")
	void testCompareFiles() throws IOException {

		final InputStream inputStream = super.getClass().getResourceAsStream("/movies-list.csv");
		final String contentMainFile = new String(inputStream.readAllBytes());

		final Path pathOriginalFile = Paths.get("src/test/resources/movies-list-original.csv");
		final String contentOriginalFile = Files.readString(pathOriginalFile);

		Assertions.assertEquals(contentMainFile, contentOriginalFile, "[1] Files must be equals!");
	}

}