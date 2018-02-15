package br.server.control.action.session;

/*
 * Document   : SessionUsuario.java
 * Created on : 20/08/2013, 21:15:36
 * Author     : Caio
 */
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionUsuario implements Serializable {

    private long nrSeqUsuarioEdicao;
    private long nrSeqSistemaEdicao;
    private long nrSeqPermissaoEdicao;
    private long nrSeqRegraEdicao;
    private long nrSeqPerfilEdicao;
    private long nrSeqUsuarioEdicaoRecurso;
    private long nrSeqPerfilEdicaoRecurso;

    public long getNrSeqUsuarioEdicao() {
        return nrSeqUsuarioEdicao;
    }

    public void setNrSeqUsuarioEdicao(long nrSeqUsuarioEdicao) {
        this.nrSeqUsuarioEdicao = nrSeqUsuarioEdicao;
    }

    public long getNrSeqSistemaEdicao() {
        return nrSeqSistemaEdicao;
    }

    public void setNrSeqSistemaEdicao(long nrSeqSistemaEdicao) {
        this.nrSeqSistemaEdicao = nrSeqSistemaEdicao;
    }

    public long getNrSeqPermissaoEdicao() {
        return nrSeqPermissaoEdicao;
    }

    public void setNrSeqPermissaoEdicao(long nrSeqPermissaoEdicao) {
        this.nrSeqPermissaoEdicao = nrSeqPermissaoEdicao;
    }

    public long getNrSeqRegraEdicao() {
        return nrSeqRegraEdicao;
    }

    public void setNrSeqRegraEdicao(long nrSeqRegraEdicao) {
        this.nrSeqRegraEdicao = nrSeqRegraEdicao;
    }

    public long getNrSeqPerfilEdicao() {
        return nrSeqPerfilEdicao;
    }

    public void setNrSeqPerfilEdicao(long nrSeqPerfilEdicao) {
        this.nrSeqPerfilEdicao = nrSeqPerfilEdicao;
    }

    public long getNrSeqUsuarioEdicaoRecurso() {
        return nrSeqUsuarioEdicaoRecurso;
    }

    public void setNrSeqUsuarioEdicaoRecurso(long nrSeqUsuarioEdicaoRecurso) {
        this.nrSeqUsuarioEdicaoRecurso = nrSeqUsuarioEdicaoRecurso;
        this.nrSeqPerfilEdicaoRecurso = 0;
    }

    public long getNrSeqPerfilEdicaoRecurso() {
        return nrSeqPerfilEdicaoRecurso;
    }

    public void setNrSeqPerfilEdicaoRecurso(long nrSeqPerfilEdicaoRecurso) {
        this.nrSeqPerfilEdicaoRecurso = nrSeqPerfilEdicaoRecurso;
        this.nrSeqUsuarioEdicaoRecurso = 0;
    }
}