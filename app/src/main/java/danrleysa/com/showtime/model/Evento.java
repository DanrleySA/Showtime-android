package danrleysa.com.showtime.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Danrley on 22/04/2017.
 */

public class Evento implements Serializable{

    private Long idEvento;
    private String descricao;
    private Usuario organizador;
    private Date dataHora;
    private Integer lotacao;
    private String local;

    public Evento() {
    }

    public Evento(Long idEvento, Usuario organizador, String descricao, String local, Date dataHora, Integer lotacao) {
        this.idEvento = idEvento;
        this.descricao = descricao;
        this.organizador = organizador;
        this.dataHora = dataHora;
        this.lotacao = lotacao;
        this.local = local;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Usuario organizador) {
        this.organizador = organizador;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    public void setLotacao(Integer lotacao) {
        this.lotacao = lotacao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evento evento = (Evento) o;

        return idEvento.equals(evento.idEvento);

    }

    @Override
    public int hashCode() {
        return idEvento.hashCode();
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", descricao='" + descricao + '\'' +
                ", organizador=" + organizador +
                ", dataHora=" + dataHora +
                ", lotacao=" + lotacao +
                ", local='" + local + '\'' +
                '}';
    }
}
