package br.com.homeoffice.helpdesk.repositories;

import br.com.homeoffice.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
