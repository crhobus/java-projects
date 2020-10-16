package br.com.app.handler;

import static br.com.app.infra.restmethods.InvokeRestMethod.SERVICO_A;

import java.math.BigDecimal;
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

import br.com.app.bempessoafisica.BemPessoaFisicaService;
import br.com.app.bempessoafisica.dto.BemPessoaFisicaDto;
import br.com.app.infra.response.Response;
import br.com.app.infra.restmethods.InvokeRestMethod;

@RestController
@RequestMapping("/api.com/bempessoafisica")
public class BemPessoaFisicaController {

    @Autowired
    private BemPessoaFisicaService service;

    @Autowired
    private InvokeRestMethod invokeRestMethod;

    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody BemPessoaFisicaDto dto, BindingResult result) {
        Response<Long> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            Response<Boolean> bolResponse = invokeRestMethod.requestMethod(SERVICO_A + "pessoafisica/existsPessoaFisica/" + dto.getCpf(), HttpMethod.GET, Boolean.class);
            if (bolResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, bolResponse);
            }

            long id = 0l;
            if (bolResponse.getData()) {
                id = service.create(dto);
            }
            response.setData(id);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody BemPessoaFisicaDto dto, BindingResult result) {
        Response<Boolean> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            Response<Boolean> bolResponse = invokeRestMethod.requestMethod(SERVICO_A + "pessoafisica/existsPessoaFisica/" + dto.getCpf(), HttpMethod.GET, Boolean.class);
            if (bolResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, bolResponse);
            }

            boolean bol = false;
            if (bolResponse.getData()) {
                bol = service.update(dto);
            }
            response.setData(bol);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/retrieve/{cpf}/{id}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<BemPessoaFisicaDto>> retrieve(@PathVariable("cpf") String cpf, @PathVariable("id") long id) {
        Response<BemPessoaFisicaDto> response = new Response<>();

        BemPessoaFisicaDto dto = service.retrieve(cpf, id);
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
    public ResponseEntity<Response<List<BemPessoaFisicaDto>>> list(@PathVariable("cpf") String cpf) {
        Response<List<BemPessoaFisicaDto>> response = new Response<>();

        List<BemPessoaFisicaDto> bens = service.list(cpf);
        response.setData(bens);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/getValorEmBensPessoaFisica/{cpf}", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<BigDecimal>> getValorEmBensPessoaFisica(@PathVariable("cpf") String cpf) {
        Response<BigDecimal> response = new Response<>();

        BigDecimal valor = service.getValorEmBensPessoaFisica(cpf);
        response.setData(valor);

        return ResponseEntity.ok(response);
    }

}
