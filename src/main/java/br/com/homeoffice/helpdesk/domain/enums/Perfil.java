package br.com.homeoffice.helpdesk.domain.enums;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer id;
    private String descricao;

    Perfil(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (Perfil x : Perfil.values()){
            if (id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido");
    }
}
