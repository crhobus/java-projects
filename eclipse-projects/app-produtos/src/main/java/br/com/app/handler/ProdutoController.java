package br.com.app.handler;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.dto.ProdutoDto;
import br.com.app.infra.response.Response;
import br.com.app.infra.utils.ModelBean;
import br.com.app.service.ProdutoService;

@RestController
@RequestMapping("/api.com/produto")
public class ProdutoController {

    @Autowired
    private ModelBean modelBean;

    @PostMapping(value = "/create")
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody ProdutoDto dto, BindingResult result) {
        Response<Long> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        try {
            long id = modelBean.get(ProdutoService.class).create(dto);
            response.setData(id);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody ProdutoDto dto, BindingResult result) {
        Response<Boolean> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        try {
            boolean bol = modelBean.get(ProdutoService.class).update(dto);
            response.setData(bol);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/retrieve/{codigo}")
    public ResponseEntity<Response<ProdutoDto>> retrieve(@PathVariable("codigo") long codigo) {
        Response<ProdutoDto> response = new Response<>();

        ProdutoDto dto = modelBean.get(ProdutoService.class).retrieve(codigo);
        response.setData(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete/{codigo}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("codigo") long codigo) {
        Response<Boolean> response = new Response<>();

        try {
            boolean bol = modelBean.get(ProdutoService.class).delete(codigo);
            response.setData(bol);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Response<List<ProdutoDto>>> list() {
        Response<List<ProdutoDto>> response = new Response<>();

        List<ProdutoDto> produtos = modelBean.get(ProdutoService.class).list();
        response.setData(produtos);

        return ResponseEntity.ok(response);
    }
}
