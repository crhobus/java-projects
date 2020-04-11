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

import br.com.app.empresa.EmpresaService;
import br.com.app.empresa.dto.EmpresaDto;
import br.com.app.infra.eventauditing.AuditedEvent;
import br.com.app.infra.response.Response;

@RestController
@RequestMapping("/api.com/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @AuditedEvent
    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody EmpresaDto dto, BindingResult result) {
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
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody EmpresaDto dto, BindingResult result) {
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

    @GetMapping(value = "/retrieve/{cnpj}", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<EmpresaDto>> retrieve(@PathVariable("cnpj") String cnpj) {
        Response<EmpresaDto> response = new Response<>();

        EmpresaDto dto = service.retrieve(cnpj);
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
    public ResponseEntity<Response<List<EmpresaDto>>> list() {
        Response<List<EmpresaDto>> response = new Response<>();

        List<EmpresaDto> empresas = service.list();
        response.setData(empresas);

        return ResponseEntity.ok(response);
    }

}
