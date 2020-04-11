package br.com.app.handler;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.infra.eventauditing.AuditedEvent;
import br.com.app.infra.response.Response;
import br.com.app.lancamento.LancamentoService;
import br.com.app.lancamento.dto.LancamentoDto;

@RestController
@RequestMapping("/api.com/lancamento")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @AuditedEvent
    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody LancamentoDto dto, BindingResult result) {
        Response<Long> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        try {
            long id = service.create(dto);
            response.setData(id);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @AuditedEvent
    @PutMapping(value = "/update", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody LancamentoDto dto, BindingResult result) {
        Response<Boolean> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        try {
            boolean bol = service.update(dto);
            response.setData(bol);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/retrieve/{id}", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<LancamentoDto>> retrieve(@PathVariable("id") long id) {
        Response<LancamentoDto> response = new Response<>();

        LancamentoDto dto = service.retrieve(id);
        response.setData(dto);

        return ResponseEntity.ok(response);
    }

    @AuditedEvent
    @DeleteMapping(value = "/delete/{id}", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("id") long id) {
        Response<Boolean> response = new Response<>();

        try {
            boolean bol = service.delete(id);
            response.setData(bol);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/list", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<LancamentoDto>>> list() {
        Response<List<LancamentoDto>> response = new Response<>();

        List<LancamentoDto> lancamentos = service.list();
        response.setData(lancamentos);

        return ResponseEntity.ok(response);
    }
}
