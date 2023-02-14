package br.com.homeoffice.helpdesk.domain.enums;

public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer id;
    private String descricao;

    Status(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (Status x : Status.values()){
            if (id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status Inválido");
    }
}
