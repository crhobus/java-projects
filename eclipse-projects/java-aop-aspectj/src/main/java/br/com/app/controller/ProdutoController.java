package br.com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.aspect.TempoExecucao;
import br.com.app.dto.Produto;
import br.com.app.service.ProdutoService;

@RestController
@RequestMapping("/api.com/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @TempoExecucao
    @PostMapping(value = "/save")
    public ResponseEntity<String> save(@Valid @RequestBody Produto dto) {
        String str = service.save(dto);
        return ResponseEntity.ok(str);
    }

    @TempoExecucao
    @GetMapping(value = "/list")
    public ResponseEntity<List<Produto>> list() {
        List<Produto> produtos = service.list();
        return ResponseEntity.ok(produtos);
    }

    @TempoExecucao
    @GetMapping(value = "/obterPrecoProduto/{codigo}")
    public ResponseEntity<Double> obterPrecoProduto(@PathVariable("codigo") int codigo) {
        try {
            double preco = service.obterPrecoProduto(codigo);
            return ResponseEntity.ok(preco);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(-1.0);
        }
    }

}
