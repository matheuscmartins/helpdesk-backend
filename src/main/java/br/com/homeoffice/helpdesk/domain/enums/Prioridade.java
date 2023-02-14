package br.com.homeoffice.helpdesk.domain.enums;

public enum Prioridade {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer id;
    private String descricao;

    Prioridade(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (Prioridade x : Prioridade.values()){
            if (id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade Inválido");
    }
}
