package br.com.homeoffice.helpdesk.resources;

import br.com.homeoffice.helpdesk.domain.Chamado;
import br.com.homeoffice.helpdesk.domain.dtos.ChamadoDTO;
import br.com.homeoffice.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        return ResponseEntity.ok().body(chamadoService.findAll().stream().map(obj -> new ChamadoDTO(obj))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        return
                ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                .buildAndExpand(chamadoService.create(chamadoDTO).getId()).toUri()
                ).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
        return ResponseEntity.ok().body(new ChamadoDTO(chamadoService.update(id, chamadoDTO)));
    }
}
