
package br.com.formiga.golden_raspberry_awards.rest.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
public class FilmDTO {

	@Transient
	@NotNull(message = "[Release Year] cannot be null!")
	@Size(min = 4, max = 4, message = "[Release Year] must be size 4!")
	private Integer releaseYear;

	@Transient
	@NotNull(message = "[Title] cannot be null!")
	@Size(min = 1, max = 250, message = "[Title] maximum size must be 250!")
	private String title;

	@Transient
	@NotNull(message = "[Studio] cannot be null!")
	@Size(min = 1, max = 250, message = "[Studio] maximum size must be 250!")
	private String studio;

	@Transient
	@NotNull(message = "[Producer] cannot be null!")
	@Size(min = 1, max = 250, message = "[Producer] maximum size must be 250!")
	private String producer;

}