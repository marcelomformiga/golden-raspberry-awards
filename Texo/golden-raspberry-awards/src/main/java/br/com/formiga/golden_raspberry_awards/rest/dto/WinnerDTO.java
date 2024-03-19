
package br.com.formiga.golden_raspberry_awards.rest.dto;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
public class WinnerDTO {

	@Transient
	@NotNull(message = "[Producers] cannot be null!")
	@Size(min = 1, max = 250, message = "[Producer]s maximum size must be 250!")
	@JsonProperty(value = "producer", required = true, access = JsonProperty.Access.READ_ONLY)
	private String producer;

	@Transient
	@NotNull(message = "[Interval] cannot be null!")
	@Size(min = 1, max = 99, message = "[Interval] must be size min 1 and max 99!")
	@JsonProperty(value = "interval", required = true, access = JsonProperty.Access.READ_ONLY)
	private Short interval;

	@Transient
	@NotNull(message = "[Previous Win] cannot be null!")
	@Size(min = 4, max = 4, message = "[Previous Win] must be size 4!")
	@JsonProperty(value = "previousWin", required = true, access = JsonProperty.Access.READ_ONLY)
	private Integer previousWin;

	@Transient
	@NotNull(message = "[Following Win] cannot be null!")
	@Size(min = 4, max = 4, message = "[Following Win] must be size 4!")
	@JsonProperty(value = "followingWin", required = true, access = JsonProperty.Access.READ_ONLY)
	private Integer followingWin;

}