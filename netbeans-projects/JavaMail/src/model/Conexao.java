package model;

//Clase de modelo com getter e setter da conexão
public class Conexao {

    private int portaPop3Imap, portaSmtp;
    private String tipo, nome, servidorPop3Imap, usuario, senha, servidorSmtp,
            email;
    private boolean autenticacao, conSegura;

    public int getPortaPop3Imap() {
        return portaPop3Imap;
    }

    public void setPortaPop3Imap(int portaPop3Imap) {
        this.portaPop3Imap = portaPop3Imap;
    }

    public int getPortaSmtp() {
        return portaSmtp;
    }

    public void setPortaSmtp(int portaSmtp) {
        this.portaSmtp = portaSmtp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServidorPop3Imap() {
        return servidorPop3Imap;
    }

    public void setServidorPop3Imap(String servidorPop3Imap) {
        this.servidorPop3Imap = servidorPop3Imap;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getServidorSmtp() {
        return servidorSmtp;
    }

    public void setServidorSmtp(String servidorSmtp) {
        this.servidorSmtp = servidorSmtp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(boolean autenticacao) {
        this.autenticacao = autenticacao;
    }

    public boolean isConSegura() {
        return conSegura;
    }

    public void setConSegura(boolean conSegura) {
        this.conSegura = conSegura;
    }
}
