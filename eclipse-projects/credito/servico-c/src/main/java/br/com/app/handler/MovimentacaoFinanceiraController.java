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
import br.com.app.movimentacaofinanceira.MovimentacaoFinanceiraService;
import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraDto;

@RestController
@RequestMapping("/api.com/movimentacaofinanceira")
public class MovimentacaoFinanceiraController {

    @Autowired
    private MovimentacaoFinanceiraService service;

    @Autowired
    private InvokeRestMethod invokeRestMethod;

    @PostMapping(value = "/create", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody MovimentacaoFinanceiraDto dto, BindingResult result) {
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
    public ResponseEntity<Response<Boolean>> update(@Valid @RequestBody MovimentacaoFinanceiraDto dto, BindingResult result) {
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
    public ResponseEntity<Response<MovimentacaoFinanceiraDto>> retrieve(@PathVariable("cpf") String cpf, @PathVariable("id") long id) {
        Response<MovimentacaoFinanceiraDto> response = new Response<>();

        MovimentacaoFinanceiraDto dto = service.retrieve(cpf, id);
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
    public ResponseEntity<Response<List<MovimentacaoFinanceiraDto>>> list(@PathVariable("cpf") String cpf) {
        Response<List<MovimentacaoFinanceiraDto>> response = new Response<>();

        List<MovimentacaoFinanceiraDto> movimentacoesFinanceiras = service.list(cpf);
        response.setData(movimentacoesFinanceiras);

        return ResponseEntity.ok(response);
    }

}
