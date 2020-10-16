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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.infra.response.Response;
import br.com.app.infra.restmethods.InvokeRestMethod;
import br.com.app.pessoafisica.PessoaFisicaService;
import br.com.app.pessoafisica.dto.PessoaFisicaDto;

@RestController
@RequestMapping("/api.com/pessoafisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService service;

    @Autowired
    private InvokeRestMethod invokeRestMethod;

    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody PessoaFisicaDto dto, BindingResult result) {
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
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody PessoaFisicaDto dto, BindingResult result) {
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

    @GetMapping(value = "/retrieve/{cpf}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<PessoaFisicaDto>> retrieve(@PathVariable("cpf") String cpf) {
        Response<PessoaFisicaDto> response = new Response<>();

        PessoaFisicaDto dto = service.retrieve(cpf);
        response.setData(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete/{cpf}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> delete(@PathVariable("cpf") String cpf) {
        Response<Boolean> response = new Response<>();

        try {
            boolean bol = service.delete(cpf);
            response.setData(bol);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/list", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<List<PessoaFisicaDto>>> list() {
        Response<List<PessoaFisicaDto>> response = new Response<>();

        List<PessoaFisicaDto> pessoasFisicas = service.list();
        response.setData(pessoasFisicas);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/getIdadePessoaFisica", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Integer>> getIdadePessoaFisica(@RequestParam("cpf") String cpf, @RequestParam("dataReferencia") String dataReferencia) {
        Response<Integer> response = new Response<>();

        try {
            Integer idade = service.getIdadePessoaFisica(cpf, dataReferencia);
            response.setData(idade);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/existsPessoaFisica/{cpf}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> existsPessoaFisica(@PathVariable("cpf") String cpf) {
        Response<Boolean> response = new Response<>();

        Boolean bol = service.existsPessoaFisica(cpf);
        response.setData(bol);

        return ResponseEntity.ok(response);
    }

}
