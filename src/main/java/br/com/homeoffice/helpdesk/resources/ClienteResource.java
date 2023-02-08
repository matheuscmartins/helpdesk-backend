package br.com.homeoffice.helpdesk.resources;

import br.com.homeoffice.helpdesk.domain.dtos.ClienteDTO;
import br.com.homeoffice.helpdesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ClienteDTO(clienteService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll().stream().map(obj -> new ClienteDTO(obj))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        return
                ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                .buildAndExpand(clienteService.create(clienteDTO).getId()).toUri()
                ).build();
    }
    @PutMapping(value = "/{id}")
    public  ResponseEntity<ClienteDTO> update (@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(new ClienteDTO(clienteService.update(id, clienteDTO)));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
