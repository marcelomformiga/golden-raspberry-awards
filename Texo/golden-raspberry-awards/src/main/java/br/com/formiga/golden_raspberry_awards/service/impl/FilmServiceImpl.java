
package br.com.formiga.golden_raspberry_awards.service.impl;


import br.com.formiga.golden_raspberry_awards.domain.model.entity.*;
import br.com.formiga.golden_raspberry_awards.domain.repository.*;
import br.com.formiga.golden_raspberry_awards.rest.dto.*;
import br.com.formiga.golden_raspberry_awards.service.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepository;

	private ModelMapper modelMapper;


	@Override
	public FilmDTO save(final FilmDTO filmDTO) {

		this.modelMapper = new ModelMapper();

		return this.modelMapper.map(this.filmRepository.save(this.modelMapper.map(filmDTO, FilmEntity.class)), FilmDTO.class);
	}

	@Override
	public List<FilmDTO> getAll() {

		this.modelMapper = new ModelMapper();

		return this.filmRepository.findAll().stream().map(filmEntity -> this.modelMapper.map(filmEntity, FilmDTO.class)).collect(Collectors.toList());
	}

}