package danrleysa.com.showtime.model;

/**
 * Created by Danrley on 22/04/2017.
 */

public class Inscricao {

    private Integer idInscricao;
    private Evento evento;
    private Usuario usuario;

    public Integer getIdInscricao() {
        return idInscricao;
    }

    public void setIdInscricao(Integer idInscricao) {
        this.idInscricao = idInscricao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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

}
