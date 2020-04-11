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

import br.com.app.funcionario.FuncionarioService;
import br.com.app.funcionario.dto.FuncionarioDto;
import br.com.app.funcionario.dto.LancamentosByFuncionarioInput;
import br.com.app.funcionario.dto.LancamentosByFuncionarioOutput;
import br.com.app.funcionario.dto.LancamentosIncorretosFuncByEmpresaInput;
import br.com.app.funcionario.dto.LancamentosIncorretosFuncByEmpresaOutput;
import br.com.app.infra.eventauditing.AuditedEvent;
import br.com.app.infra.response.Response;

@RestController
@RequestMapping("/api.com/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @AuditedEvent
    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody FuncionarioDto dto, BindingResult result) {
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
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody FuncionarioDto dto, BindingResult result) {
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

    @GetMapping(value = "/retrieve/{cpf}", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<FuncionarioDto>> retrieve(@PathVariable("cpf") String cpf) {
        Response<FuncionarioDto> response = new Response<>();

        FuncionarioDto dto = service.retrieve(cpf);
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
    public ResponseEntity<Response<List<FuncionarioDto>>> list() {
        Response<List<FuncionarioDto>> response = new Response<>();

        List<FuncionarioDto> funcionarios = service.list();
        response.setData(funcionarios);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/lancamentosFuncPorData", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<LancamentosByFuncionarioOutput>> lancamentosFuncPorData(@Valid LancamentosByFuncionarioInput input, BindingResult result) {
        Response<LancamentosByFuncionarioOutput> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        try {
            LancamentosByFuncionarioOutput output = service.listLancamentosFuncPorData(input.getCpf(), input.getData());
            response.setData(output);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/lancamentosIncorretosFuncPorEmpresa", headers = { "X-API-Version=V1" })
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<LancamentosIncorretosFuncByEmpresaOutput>>> lancamentosIncorretosFuncPorEmpresa(@Valid LancamentosIncorretosFuncByEmpresaInput input, BindingResult result) {
        Response<List<LancamentosIncorretosFuncByEmpresaOutput>> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        try {
            List<LancamentosIncorretosFuncByEmpresaOutput> output = service.listLancamentosIncorretosFuncPorEmpresa(input.getCnpj(), input.getData());
            response.setData(output);
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}
