
package br.com.formiga.golden_raspberry_awards.rest.dto;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
@JsonRootName(value = "Awards")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WinnerPrizeRangeDTO {

	@Transient
	@NotNull(message = "[Min] cannot be null!")
	@JsonProperty(value = "min", required = true, access = JsonProperty.Access.READ_ONLY)
	private List<WinnerDTO> min;

	@Transient
	@NotNull(message = "[Max] cannot be null!")
	@JsonProperty(value = "max", required = true, access = JsonProperty.Access.READ_ONLY)
	private List<WinnerDTO> max;

}