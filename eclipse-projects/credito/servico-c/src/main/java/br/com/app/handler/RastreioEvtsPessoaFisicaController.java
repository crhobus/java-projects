package br.com.app.handler;

import static br.com.app.infra.restmethods.InvokeRestMethod.SERVICO_A;
import static br.com.app.infra.restmethods.InvokeRestMethod.SERVICO_B;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.infra.response.Response;
import br.com.app.infra.restmethods.InvokeRestMethod;
import br.com.app.rastreioevtspessoafisica.RastreioEvtsPessoaFisicaService;
import br.com.app.rastreioevtspessoafisica.dto.NovaConsultaCreditoInput;
import br.com.app.rastreioevtspessoafisica.dto.NovaConsultaCreditoOutput;
import br.com.app.rastreioevtspessoafisica.dto.PessoaFisicaDto;
import br.com.app.rastreioevtspessoafisica.dto.PossuiDividaEnum;
import br.com.app.rastreioevtspessoafisica.dto.RendaPessoaFisicaDto;

@RestController
@RequestMapping("/api.com/rastreioevtspessoafisica")
public class RastreioEvtsPessoaFisicaController {

    @Autowired
    private RastreioEvtsPessoaFisicaService service;

    @Autowired
    private InvokeRestMethod invokeRestMethod;

    @PostMapping(value = "/gerarNovaConultaCredito", headers = { "X-API-Version=V1" })
    public ResponseEntity<Response<NovaConsultaCreditoOutput>> gerarNovaConultaCredito(@Valid @RequestBody NovaConsultaCreditoInput dto, BindingResult result) {
        Response<NovaConsultaCreditoOutput> response = new Response<>();

        if (result.hasErrors()) {
            return invokeRestMethod.getResponseErrors(response, result);
        }

        try {
            Response<PessoaFisicaDto> pessoaFisicaResponse = invokeRestMethod.requestMethod(SERVICO_A + "pessoafisica/retrieve/" + dto.getCpf(), HttpMethod.GET, PessoaFisicaDto.class);
            if (pessoaFisicaResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, pessoaFisicaResponse);
            }

            Response<Boolean> possuiDividaResponse = invokeRestMethod.requestMethod(SERVICO_A + "divida/possuiDivida/" + dto.getCpf(), HttpMethod.GET, Boolean.class);
            if (possuiDividaResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, possuiDividaResponse);
            }

            Response<List<RendaPessoaFisicaDto>> ultimosRegistrosRendaResponse = invokeRestMethod.requestMethod(SERVICO_B + "rendapessoafisica/getUltimosRegistrosRenda/" + dto.getCpf() + "/" + 3, HttpMethod.GET, new ArrayList<RendaPessoaFisicaDto>().getClass());
            if (ultimosRegistrosRendaResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, ultimosRegistrosRendaResponse);
            }

            Response<BigDecimal> valorEmBensResponse = invokeRestMethod.requestMethod(SERVICO_B + "bempessoafisica/getValorEmBensPessoaFisica/" + dto.getCpf(), HttpMethod.GET, BigDecimal.class);
            if (valorEmBensResponse.hasErrors()) {
                return invokeRestMethod.getResponseErrors(response, valorEmBensResponse);
            }

            NovaConsultaCreditoOutput output = service.gerarNovaConultaCredito(dto, //
                                                                               pessoaFisicaResponse.getData(), //
                                                                               possuiDividaResponse.getData() ? PossuiDividaEnum.SIM : PossuiDividaEnum.NAO, //
                                                                               ultimosRegistrosRendaResponse.getData(), //
                                                                               valorEmBensResponse.getData());
            response.setData(output);
        } catch (Exception ex) {
            return invokeRestMethod.getResponseErrors(response, ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

}
