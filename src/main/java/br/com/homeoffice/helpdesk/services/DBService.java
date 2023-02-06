package br.com.homeoffice.helpdesk.services;

import br.com.homeoffice.helpdesk.domain.Chamado;
import br.com.homeoffice.helpdesk.domain.Cliente;
import br.com.homeoffice.helpdesk.domain.Tecnico;
import br.com.homeoffice.helpdesk.domain.enums.Perfil;
import br.com.homeoffice.helpdesk.domain.enums.Prioridade;
import br.com.homeoffice.helpdesk.domain.enums.Status;
import br.com.homeoffice.helpdesk.repositories.ChamadoRepository;
import br.com.homeoffice.helpdesk.repositories.ClienteRepository;
import br.com.homeoffice.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instantializeDb() {
        Tecnico tec1 = new Tecnico(null, "Matheus Cruz", "63653230268", "matheuscruz@mail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Maria Green", "80527954780", "mariagreen@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
                "Chamado 01", "Primeiro Chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
