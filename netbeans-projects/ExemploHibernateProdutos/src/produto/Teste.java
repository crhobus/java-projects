package produto;

import java.util.List;

public class Teste {

    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando a conexão com o banco de dados");
        ProdutoDAO dao = new ProdutoDAO();

        System.out.println("Criando os produtos.........");
        Produto p;
        for (int i = 0; i < 100; i++) {
            p = new Produto();
            p.setNome("Teste");
            p.setDescricao("Teste descrição");
            p.setPreco(100.40);
            dao.salva(p);
        }
        System.out.println("produtos criados.........");

        System.out.println("");

        p = dao.procura(12);
        System.out.println("Removendo produto: " + p.getCodigo() + " nome: " + p.getNome() + " descrição: " + p.getDescricao() + " preço: " + p.getPreco());
        dao.remove(p);

        System.out.println("");

        p = dao.procura(14);
        System.out.println("Atualizando produto: " + p.getCodigo() + " nome: " + p.getNome() + " descrição: " + p.getDescricao() + " preço: " + p.getPreco());
        p.setPreco(20000.56);
        System.out.println("para o novo preço " + p.getPreco());
        dao.atualiza(p);

        System.out.println("");

        System.out.println("Listando produtos que tem o seu código até 20.........");
        List<Produto> produtos = dao.listaProdutosComClausulaWhere();
        for (Produto prod : produtos) {
            System.out.println("Produto: " + prod.getCodigo() + " nome: " + prod.getNome() + " descrição: " + prod.getDescricao() + " preço: " + prod.getPreco());
        }

        System.out.println("");

        System.out.println("Listando produtos utilizando paginação de 20 e listando a partir do décimo registro.........");
        produtos = dao.listaProdutosPaginacao(10, 20);
        for (Produto prod : produtos) {
            System.out.println("Produto: " + prod.getCodigo() + " nome: " + prod.getNome() + " descrição: " + prod.getDescricao() + " preço: " + prod.getPreco());
        }

        System.out.println("");

        System.out.println("Listando todos os produtos cadastrados.........");
        produtos = dao.listaProdutos();
        for (Produto prod : produtos) {
            System.out.println("Produto: " + prod.getCodigo() + " nome: " + prod.getNome() + " descrição: " + prod.getDescricao() + " preço: " + prod.getPreco());
        }

        System.out.println("");

        System.out.println("Encerrando a conexão com o banco de dados");
        dao.close();
    }
}
