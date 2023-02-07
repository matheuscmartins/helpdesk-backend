package br.com.homeoffice.helpdesk.services;

import br.com.homeoffice.helpdesk.domain.Tecnico;
import br.com.homeoffice.helpdesk.domain.dtos.TecnicoDTO;
import br.com.homeoffice.helpdesk.repositories.TecnicoRepository;
import br.com.homeoffice.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        return tecnicoRepository.save(new Tecnico(tecnicoDTO));
    }
}
