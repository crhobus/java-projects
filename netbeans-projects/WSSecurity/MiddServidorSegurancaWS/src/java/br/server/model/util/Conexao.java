package br.server.model.util;

/*
 * Document   : Conexao.java
 * Created on : 25/05/2013, 08:50:45
 * Author     : Caio
 */
import br.server.model.util.repository.UsuarioRepository;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = {"FacesServlet"})
public class Conexao implements Filter {

    private EntityManagerFactory factory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        factory = Persistence.createEntityManagerFactory("MiddServidorSegurancaWSPU");
        UsuarioRepository usuarioRepository = new UsuarioRepository(new ConexaoUtil());
        usuarioRepository.inserirUsuarioPadrao(this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        EntityManager manager = factory.createEntityManager();
        request.setAttribute("EntityManager", manager);
        manager.getTransaction().begin();

        chain.doFilter(request, response);

        try {
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    @Override
    public void destroy() {
        factory.close();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }
}