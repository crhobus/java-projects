package com.utils.java8.cap11UsandoOsConceitos;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author crhobus
 */
public class Sistema {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente1");
        Cliente cliente2 = new Cliente("Cliente2");
        Cliente cliente3 = new Cliente("Cliente3");
        Cliente cliente4 = new Cliente("Cliente4");

        Produto produto1 = new Produto("Song 12", Paths.get("C:\\Users\\crhobus\\Downloads\\Musicas\\Song 12.mp3"), new BigDecimal(100));
        Produto produto2 = new Produto("Song 14", Paths.get("C:\\Users\\crhobus\\Downloads\\Musicas\\Song 14.mp3"), new BigDecimal(90));
        Produto produto3 = new Produto("Papas da Lingua - Disco rock", Paths.get("C:\\Users\\crhobus\\Downloads\\Musicas\\papas da lingua  - disco rock.mp3"), new BigDecimal(50));
        Produto produto4 = new Produto("Run Dmc & Aerosmith - Walk This Way", Paths.get("C:\\Users\\crhobus\\Downloads\\Musicas\\Run Dmc & Aerosmith - Walk This Way.mp3"), new BigDecimal(150));
        Produto produto5 = new Produto("Oasis - Wonderwall", Paths.get("C:\\Users\\crhobus\\Downloads\\Musicas\\Oasis - Wonderwall.mp3"), new BigDecimal(200));
        Produto produto6 = new Produto("Elvis Presley - Jailhouse Rock", Paths.get("C:\\Users\\crhobus\\Downloads\\Musicas\\Elvis Presley - Jailhouse Rock.mp3"), new BigDecimal(100));

        LocalDateTime dtAtual = LocalDateTime.now();
        LocalDateTime dtOntem = dtAtual.minusDays(1);
        LocalDateTime dtMesPassado = dtAtual.minusMonths(1);

        Compra compra1 = new Compra(asList(produto1, produto2), dtAtual, cliente1);
        Compra compra2 = new Compra(asList(produto1, produto3, produto6), dtOntem, cliente2);
        Compra compra3 = new Compra(asList(produto4, produto5, produto1), dtAtual, cliente4);
        Compra compra4 = new Compra(asList(produto1, produto2, produto6), dtMesPassado, cliente3);
        Compra compra5 = new Compra(asList(produto4, produto6), dtOntem, cliente1);

        List<Compra> compras = asList(compra1, compra2, compra3, compra4, compra5);

        compras.stream()
                .sorted(Comparator.comparing(Compra::getData))
                .forEach(System.out::println);

        compra1.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal::add)
                .ifPresent(System.out::println);

        BigDecimal total = compra1.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(total);

        Stream<BigDecimal> precosStream = compras.stream()
                .map(p -> p.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        total = compras.stream()
                .map(p -> p.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(total);

        Stream<BigDecimal> precoCadaProduto = compras.stream()
                .flatMap(p -> p.getProdutos().stream().map(Produto::getPreco));

        //precoCadaProduto passo-a-passo
        Function<Compra, Stream<BigDecimal>> mapper
                = p -> p.getProdutos().stream().map(Produto::getPreco);

        BigDecimal totalFlat = compras.stream()
                .flatMap(p -> p.getProdutos().stream().map(Produto::getPreco))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(totalFlat);

        Stream<Produto> produtosStream = compras.stream()
                .map(Compra::getProdutos)
                .flatMap(p -> p.stream());

        produtosStream = compras.stream()
                .map(Compra::getProdutos)
                .flatMap(List::stream);

        produtosStream = compras.stream()
                .flatMap(p -> p.getProdutos().stream());

        Map<Produto, Long> produtosMaisVendidos = compras.stream()
                .flatMap(p -> p.getProdutos().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(produtosMaisVendidos);

        produtosMaisVendidos.entrySet()
                .stream().forEach(System.out::println);

        System.out.println("");

        produtosMaisVendidos.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(System.out::println);

        System.out.println("");

        Map<Produto, BigDecimal> valorTotalPorProduto = compras.stream()
                .flatMap(p -> p.getProdutos().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(BigDecimal.ZERO, Produto::getPreco, BigDecimal::add)));
        valorTotalPorProduto.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);

        System.out.println("");

        Map<Cliente, List<Compra>> clientePorCompra = compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente));

        Map<Cliente, List<List<Produto>>> listaProdutosPorCliente = compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente, Collectors.mapping(Compra::getProdutos, Collectors.toList())));

        listaProdutosPorCliente.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getNome()))
                .forEach(System.out::println);

        System.out.println("");

        Map<Cliente, List<Produto>> listaProdutosPorCliente2steps = listaProdutosPorCliente.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .flatMap(List::stream)
                                .collect(Collectors.toList())));

        listaProdutosPorCliente2steps.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getNome()))
                .forEach(System.out::println);

        //código acima em uma mesma chamada
        listaProdutosPorCliente2steps = compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente, Collectors.mapping(Compra::getProdutos, Collectors.toList())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .flatMap(List::stream)
                                .collect(Collectors.toList())));

        listaProdutosPorCliente2steps.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getNome()))
                .forEach(System.out::println);

        //Código acima por passos
        listaProdutosPorCliente2steps = compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente,
                        Collectors.reducing(Collections.emptyList(),
                                Compra::getProdutos,
                                (l1, l2) -> {
                                    List<Produto> l = new ArrayList<>();
                                    l.addAll(l1);
                                    l.addAll(l2);
                                    return l;
                                })));

        listaProdutosPorCliente2steps.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().getNome()))
                .forEach(System.out::println);

        System.out.println("");

        Map<Cliente, BigDecimal> valorTotalPorCliente = compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente,
                        Collectors.reducing(BigDecimal.ZERO,
                                p -> p.getProdutos().stream().map(Produto::getPreco)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                                BigDecimal::add)));

        //Código acima por passos
        Function<Compra, BigDecimal> totalCompra = p -> p.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        valorTotalPorCliente = compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente,
                        Collectors.reducing(BigDecimal.ZERO,
                                totalCompra,
                                BigDecimal::add)));

        valorTotalPorCliente.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);

        System.out.println("");

        Map<YearMonth, List<Compra>> comprasPorMes = compras.stream()
                .collect(Collectors.groupingBy(p -> YearMonth.from(p.getData())));
        comprasPorMes.entrySet().stream()
                .forEach(System.out::println);

        Map<YearMonth, BigDecimal> valorComprasPorMes = compras.stream()
                .collect(Collectors.groupingBy(p -> YearMonth.from(p.getData()),
                        Collectors.reducing(BigDecimal.ZERO,
                                p -> p.getProdutos().stream()
                                        .map(Produto::getPreco)
                                        .reduce(BigDecimal.ZERO,
                                                BigDecimal::add),
                                BigDecimal::add)));
        valorComprasPorMes.entrySet().stream()
                .forEach(System.out::println);

        System.out.println("");

        BigDecimal taxaMensal = new BigDecimal("99.90");

        Assinatura s1 = new Assinatura(taxaMensal, dtOntem.minusMonths(5), cliente1);
        Assinatura s2 = new Assinatura(taxaMensal, dtOntem.minusMonths(8), dtAtual.minusMonths(1), cliente2);
        Assinatura s3 = new Assinatura(taxaMensal, dtOntem.minusMonths(5), dtAtual.minusMonths(2), cliente4);

        List<Assinatura> assinaturas = asList(s1, s2, s3);

        long meses = ChronoUnit.MONTHS.between(s1.getDtInicio(), LocalDateTime.now());
        System.out.println(meses);

        meses = ChronoUnit.MONTHS.between(s1.getDtInicio(), s1.getDtfim().orElse(LocalDateTime.now()));
        System.out.println(meses);

        System.out.println("");

        total = s1.getTaxaMensal()
                .multiply(new BigDecimal(ChronoUnit.MONTHS
                        .between(s1.getDtInicio(), s1.getDtfim().orElse(LocalDateTime.now()))));
        System.out.println(total);

        //ou
        System.out.println(s1.obterVlTotalPago());

        System.out.println("");

        BigDecimal totalPago = assinaturas.stream()
                .map(Assinatura::obterVlTotalPago)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(totalPago);
    }
}
