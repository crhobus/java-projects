package br.com.app.handler;

import static br.com.app.infra.restmethods.InvokeRestMethod.SERVICO_A;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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

import br.com.app.infra.response.Response;
import br.com.app.infra.restmethods.InvokeRestMethod;
import br.com.app.rendapessoafisica.RendaPessoaFisicaService;
import br.com.app.rendapessoafisica.dto.RendaPessoaFisicaDto;
import br.com.app.rendapessoafisica.dto.RendaPessoaFisicaOutput;

@RestController
@RequestMapping("/api.com/rendapessoafisica")
public class RendaPessoaFisicaController {

    @Autowired
    private RendaPessoaFisicaService service;

    @Autowired
    private InvokeRestMethod invokeRestMethod;

    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody RendaPessoaFisicaDto dto, BindingResult result) {
        Response<Long> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            Response<Integer> idadeResponse = invokeRestMethod.requestMethod(SERVICO_A + "pessoafisica/getIdadePessoaFisica?cpf=" + dto.getCpf() + "&dataReferencia=" + dto.getDataRenda(), HttpMethod.GET, Integer.class);
            if (idadeResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, idadeResponse);
            }

            dto.setIdade(idadeResponse.getData());
            long id = service.create(dto);
            response.setData(id);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody RendaPessoaFisicaDto dto, BindingResult result) {
        Response<Boolean> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            Response<Integer> idadeResponse = invokeRestMethod.requestMethod(SERVICO_A + "pessoafisica/getIdadePessoaFisica?cpf=" + dto.getCpf() + "&dataReferencia=" + dto.getDataRenda(), HttpMethod.GET, Integer.class);
            if (idadeResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, idadeResponse);
            }

            dto.setIdade(idadeResponse.getData());
            boolean bol = service.update(dto);
            response.setData(bol);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/retrieve/{cpf}/{id}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<RendaPessoaFisicaDto>> retrieve(@PathVariable("cpf") String cpf, @PathVariable("id") long id) {
        Response<RendaPessoaFisicaDto> response = new Response<>();

        RendaPessoaFisicaDto dto = service.retrieve(cpf, id);
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

    @GetMapping(value = "/list", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<List<RendaPessoaFisicaDto>>> list() {
        Response<List<RendaPessoaFisicaDto>> response = new Response<>();

        List<RendaPessoaFisicaDto> rendaPessoasFisicas = service.list();
        response.setData(rendaPessoasFisicas);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/getUltimosRegistrosRenda/{cpf}/{quantidade}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<List<RendaPessoaFisicaOutput>>> getUltimosRegistrosRenda(@PathVariable("cpf") String cpf, @PathVariable("quantidade") int quantidade) {
        Response<List<RendaPessoaFisicaOutput>> response = new Response<>();

        List<RendaPessoaFisicaOutput> ultimosRegistrosRenda = service.getUltimosRegistrosRenda(cpf, quantidade);
        response.setData(ultimosRegistrosRenda);

        return ResponseEntity.ok(response);
    }

}
