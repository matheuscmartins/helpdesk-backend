package br.com.homeoffice.helpdesk.services;

import br.com.homeoffice.helpdesk.domain.Cliente;
import br.com.homeoffice.helpdesk.domain.dtos.ClienteDTO;
import br.com.homeoffice.helpdesk.repositories.PessoaRepository;
import br.com.homeoffice.helpdesk.repositories.ClienteRepository;
import br.com.homeoffice.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.homeoffice.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        clienteDTO.setSenha(bCryptPasswordEncoder.encode(clienteDTO.getSenha()));
        validaPorCpfEmail(clienteDTO);
        return clienteRepository.save(new Cliente(clienteDTO));
    }

    public Cliente update(Integer id, @Valid ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldObj = findById(id);

        if(!clienteDTO.getSenha().equals(oldObj.getSenha())){
            clienteDTO.setSenha(bCryptPasswordEncoder.encode(clienteDTO.getSenha()));
        }

        validaPorCpfEmail(clienteDTO);
        oldObj = new Cliente(clienteDTO);
        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("O Cliente possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    private void validaPorCpfEmail(ClienteDTO clienteDTO) {
        if (pessoaRepository.findByCpf(clienteDTO.getCpf()).isPresent() &&
                pessoaRepository.findByCpf(clienteDTO.getCpf()).get().getId() != clienteDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema! CPF: " + clienteDTO.getCpf());
        }
        if (pessoaRepository.findByEmail(clienteDTO.getEmail()).isPresent() &&
                pessoaRepository.findByEmail(clienteDTO.getEmail()).get().getId() != clienteDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema! Email: " + clienteDTO.getEmail());
        }
    }
}
