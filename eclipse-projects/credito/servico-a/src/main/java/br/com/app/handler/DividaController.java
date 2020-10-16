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

import br.com.app.divida.DividaService;
import br.com.app.divida.dto.DividaDto;
import br.com.app.divida.dto.IdDividaDto;
import br.com.app.infra.response.Response;
import br.com.app.infra.restmethods.InvokeRestMethod;

@RestController
@RequestMapping("/api.com/divida")
public class DividaController {

    @Autowired
    private DividaService service;

    @Autowired
    private InvokeRestMethod invokeRestMethod;

    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody DividaDto dto, BindingResult result) {
        Response<Long> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            long id = service.create(dto);
            response.setData(id);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody DividaDto dto, BindingResult result) {
        Response<Boolean> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            boolean bol = service.update(dto);
            response.setData(bol);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/retrieve/{cpf}/{id}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<DividaDto>> retrieve(@PathVariable("cpf") String cpf, @PathVariable("id") long id) {
        Response<DividaDto> response = new Response<>();

        DividaDto dto = service.retrieve(cpf, id);
        response.setData(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete/{cpf}/{id}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("cpf") String cpf, @PathVariable("id") long id) {
        Response<Boolean> response = new Response<>();

        try {
            boolean bol = service.delete(cpf, id);
            response.setData(bol);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/list/{cpf}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<List<DividaDto>>> list(@PathVariable("cpf") String cpf) {
        Response<List<DividaDto>> response = new Response<>();

        List<DividaDto> dividas = service.list(cpf);
        response.setData(dividas);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/possuiDivida/{cpf}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> possuiDivida(@PathVariable("cpf") String cpf) {
        Response<Boolean> response = new Response<>();

        try {
            boolean bol = service.possuiDivida(cpf);
            response.setData(bol);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/quitarDivida", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> quitarDivida(@Valid @RequestBody IdDividaDto dto, BindingResult result) {
        Response<Boolean> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            boolean bol = service.quitarDivida(dto.getCpf(), dto.getId());
            response.setData(bol);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }
}
