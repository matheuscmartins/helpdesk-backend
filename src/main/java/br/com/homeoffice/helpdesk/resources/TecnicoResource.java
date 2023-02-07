package br.com.homeoffice.helpdesk.resources;

import br.com.homeoffice.helpdesk.domain.dtos.TecnicoDTO;
import br.com.homeoffice.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new TecnicoDTO(tecnicoService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        return ResponseEntity.ok().body(tecnicoService.findAll().stream().map(obj -> new TecnicoDTO(obj))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        return
                ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                .buildAndExpand(tecnicoService.create(tecnicoDTO).getId()).toUri()
                ).build();
    }
    @PutMapping(value = "/{id}")
    public  ResponseEntity<TecnicoDTO> update (@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO){
        return ResponseEntity.ok().body(new TecnicoDTO(tecnicoService.update(id, tecnicoDTO)));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
