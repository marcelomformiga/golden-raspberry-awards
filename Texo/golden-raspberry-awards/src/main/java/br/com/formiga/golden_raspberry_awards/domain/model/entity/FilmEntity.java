
package br.com.formiga.golden_raspberry_awards.domain.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.*;


@Entity
@Table(name = "film")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
public class FilmEntity implements Serializable {

	private static final long serialVersionUID = -4719703680543447808L;

	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "release_year", nullable = false, length = 4)
	private Integer releaseYear;

	@Column(name = "title", nullable = false, length = 250)
	private String title;

	@Column(name = "studio", nullable = false, length = 250)
	private String studio;

	@Column(name = "producer", nullable = false, length = 250)
	private String producer;

}