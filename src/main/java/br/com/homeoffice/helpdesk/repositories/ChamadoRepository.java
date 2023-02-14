package br.com.homeoffice.helpdesk.repositories;

import br.com.homeoffice.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
