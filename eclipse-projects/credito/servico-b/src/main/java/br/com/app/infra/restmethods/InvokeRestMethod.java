package br.com.app.infra.restmethods;

import java.lang.reflect.Type;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.app.infra.response.Response;

@Component
public class InvokeRestMethod {

    public static final String SERVICO_A = "http://localhost:8084/api.com/";

    public <T> Response<T> requestMethod(String url, HttpMethod method, Class<?> clazz) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Version", "V1");

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ParameterizedTypeReference<Response<T>> typeRef = ParameterizedTypeReference.forType(ResolvableType.forClassWithGenerics(Response.class, clazz).getType());

        try {
            ResponseEntity<Response<T>> response = restTemplate.exchange(url, method, entity, typeRef);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            Type t = new TypeToken<Response<T>>() {}.getType();
            return new Gson().fromJson(ex.getResponseBodyAsString(), t);
        }
    }

    public <T> ResponseEntity<Response<T>> getResponseErrors(Response<T> resPrimitive, Response<?> resInvokeMethod) {
        resInvokeMethod.getErrors().forEach(error -> resPrimitive.getErrors().add(error));
        return ResponseEntity.badRequest().body(resPrimitive);
    }

    public <T> ResponseEntity<Response<T>> getResponseErrors(Response<T> resPrimitive, BindingResult bindingResult) {
        bindingResult.getAllErrors().forEach(error -> resPrimitive.getErrors().add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(resPrimitive);
    }

    public <T> ResponseEntity<Response<T>> getResponseErrors(Response<T> resPrimitive, String message) {
        resPrimitive.getErrors().add(message);
        return ResponseEntity.badRequest().body(resPrimitive);
    }

}
