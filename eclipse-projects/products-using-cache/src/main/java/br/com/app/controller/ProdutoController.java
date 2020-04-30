package br.com.app.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.app.infra.response.RestResponse;
import br.com.app.service.ProdutoService;

@RestController
@RequestMapping("/api.com/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(value = "/create")
    public ResponseEntity<Response<ProdutoDto>> create(@Valid @RequestBody ProdutoDto dto, BindingResult result) {
        RestResponse<ProdutoDto> response = new RestResponse<>();

        Optional<ResponseEntity<Response<ProdutoDto>>> errosOpt = response.verifyErrors(result);
        if (errosOpt.isPresent()) {
            return errosOpt.get();
        }

        try {
            dto = service.create(dto);
            return response.success(dto);
        } catch (Exception ex) {
            return response.error(ex.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Response<ProdutoDto>> update(@Valid @RequestBody ProdutoDto dto, BindingResult result) {
        RestResponse<ProdutoDto> response = new RestResponse<>();

        Optional<ResponseEntity<Response<ProdutoDto>>> errosOpt = response.verifyErrors(result);
        if (errosOpt.isPresent()) {
            return errosOpt.get();
        }

        try {
            dto = service.update(dto);
            return response.success(dto);
        } catch (Exception ex) {
            return response.error(ex.getMessage());
        }
    }

    @GetMapping(value = "/retrieve/{codigo}")
    public ResponseEntity<Response<ProdutoDto>> retrieve(@PathVariable("codigo") long codigo) {
        RestResponse<ProdutoDto> response = new RestResponse<>();

        ProdutoDto dto = service.retrieve(codigo);
        return response.success(dto);
    }

    @DeleteMapping(value = "/delete/{codigo}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("codigo") long codigo) {
        RestResponse<Boolean> response = new RestResponse<>();

        try {
            boolean bol = service.delete(codigo);
            return response.success(bol);
        } catch (Exception ex) {
            return response.error(ex.getMessage());
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Response<List<ProdutoDto>>> list() {
        RestResponse<List<ProdutoDto>> response = new RestResponse<>();

        List<ProdutoDto> produtos = service.list();
        return response.success(produtos);
    }
}
