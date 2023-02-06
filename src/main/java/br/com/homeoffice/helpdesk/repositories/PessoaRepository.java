package br.com.homeoffice.helpdesk.repositories;

import br.com.homeoffice.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
