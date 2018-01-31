package server.pedido;

import control.Servidor;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidades.ItemPedido;
import model.entidades.Pedido;
import model.entidades.Sabor;
import model.entidades.Tamanho;
import server.Autenticar;
import server.Index;

public class ServletPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Autenticar autenticar = new Autenticar();
        if (autenticar.executaAutenticacao(request, request.getParameter("bt_ok_pedido"), request.getParameter("bt_sair_pedido"))) {
            RequestDispatcher rd = request.getRequestDispatcher("pedido.jsp");
            rd.forward(request, response);
            return;
        }
        Servidor servidor = null;
        String mensagem = "";
        try {
            servidor = Index.getInstanceServidor();
        } catch (ExceptionError ex) {
            System.out.println("Erro ao conectar ao banco!");
            mensagem = ex.getMessage();
        }

        if ("Continuar".equalsIgnoreCase(request.getParameter("bt_continuar"))) {

            Pedido p = new Pedido();
            p.setCliente(Index.getClienteLogado());
            p.setDtCadastro(new Date());
            p.setDsObservacao("Sem cebola");

            if (p.getCliente() == null) {
                request.setAttribute("msg", "É necessário estar logado para cadastrar pedidos!");

                //fazer o que falta aqui
                RequestDispatcher rd = request.getRequestDispatcher("pedido.jsp");
                rd.forward(request, response);

                return;
            }

            int qtd = Integer.parseInt(request.getParameter("numPizzas"));
            try {
                qtd = 1;

                for (int i = 1; i <= qtd; i++) {
                    // Item Pedido
                    ItemPedido item = new ItemPedido();
                    int tamanho = Integer.parseInt(request.getParameter("tamanho" + 1));

                    Tamanho tam = new Tamanho();
                    tam.setCdTamanho(tamanho);
                    tam.setDsTamanho("Teste");
                    tam.setTmAtivo(1);
                    //  Tamanho tam = servidor.getTamanhoAction().getTamanho(tamanho);
                    tam.setCdTamanho(tamanho);
                    item.setTamanho(tam);
                    Sabor sabor = new Sabor();

                    for (int x = 1; x <= 4; x++) {
                        String stCodSabor = request.getParameter("1-" + x);
                        if (!stCodSabor.equals("")) {
                            int codSabor = Integer.parseInt(stCodSabor);
                            sabor = new Sabor();
                            sabor.setCdSabor(codSabor);
                            sabor.setNmSabor("Sabor teste");
                            item.addSabor(sabor);
                        } else {
                            // sai do for
                            x = 999;
                        }
                    }

                //   p.addItem(item);
                }

                servidor.getPedidoAction().salvar(p);
            } catch (ExceptionInfo ex) {
                Logger.getLogger(ServletPedido.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ServletPedido.class.getName()).log(Level.SEVERE, null, ex);
            }

            //fazer o que falta aqui
            RequestDispatcher rd = request.getRequestDispatcher("pedido.jsp");
            rd.forward(request, response);
            return;
        }
        List<Sabor> sabores = null;

        try {
            sabores = servidor.getSaborAction().getSabores();
        } catch (ExceptionError ex) {
            System.out.println("Erro ao buscar dados no database!");
        }

        request.setAttribute("dados", "  ");
        request.setAttribute("msg", mensagem);
        request.setAttribute("sabores", sabores);

        //fazer o que falta aqui
        RequestDispatcher rd = request.getRequestDispatcher("pedido.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);



    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
