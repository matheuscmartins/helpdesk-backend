package br.com.homeoffice.helpdesk.resources;

import br.com.homeoffice.helpdesk.domain.Chamado;
import br.com.homeoffice.helpdesk.domain.dtos.ChamadoDTO;
import br.com.homeoffice.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }
}
