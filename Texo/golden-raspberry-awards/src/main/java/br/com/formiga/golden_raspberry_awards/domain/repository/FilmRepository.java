
package br.com.formiga.golden_raspberry_awards.domain.repository;


import br.com.formiga.golden_raspberry_awards.domain.model.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;


@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

}