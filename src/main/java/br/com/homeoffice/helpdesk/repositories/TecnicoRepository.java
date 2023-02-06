package br.com.homeoffice.helpdesk.repositories;

import br.com.homeoffice.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
