package br.com.homeoffice.helpdesk.domain.dtos;

import br.com.homeoffice.helpdesk.domain.Tecnico;
import br.com.homeoffice.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "O Campo NOME é obrigatorio")
    protected String nome;
    @NotNull(message = "O Campo CPF é obrigatorio")
    protected String cpf;
    @NotNull(message = "O Campo E-MAIL é obrigatorio")
    protected String email;
    @NotNull(message = "O Campo SENHA é obrigatorio")
    protected String senha;
    protected Set<Integer> perfils = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();
    public TecnicoDTO(){
        super();
        addPerfils(Perfil.CLIENTE);
    }

    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfils = obj.getPerfils().stream().map(x -> x.getId()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfils(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfils() {
        return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfils(Perfil perfil) {
        this.perfils.add(perfil.getId());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
