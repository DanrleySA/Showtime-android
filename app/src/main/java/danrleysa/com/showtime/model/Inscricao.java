package danrleysa.com.showtime.model;

import java.io.Serializable;

/**
 * Created by Danrley on 22/04/2017.
 */

public class Inscricao implements Serializable{

    private Long idInscricao;
    private Evento evento;
    private Usuario usuario;

    public Inscricao() {
    }

    public Inscricao(Long idInscricao, Evento evento, Usuario usuario) {
        this.idInscricao = idInscricao;
        this.evento = evento;
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inscricao inscricao = (Inscricao) o;

        return idInscricao.equals(inscricao.idInscricao);

    }

    @Override
    public int hashCode() {
        return idInscricao.hashCode();
    }

    @Override
    public String toString() {
        return "Inscricao{" +
                "idInscricao=" + idInscricao +
                ", evento=" + evento +
                ", usuario=" + usuario +
                '}';
    }
}
