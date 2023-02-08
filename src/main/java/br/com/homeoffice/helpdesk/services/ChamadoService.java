package br.com.homeoffice.helpdesk.services;

import br.com.homeoffice.helpdesk.domain.Chamado;
import br.com.homeoffice.helpdesk.repositories.ChamadoRepository;
import br.com.homeoffice.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }
}
