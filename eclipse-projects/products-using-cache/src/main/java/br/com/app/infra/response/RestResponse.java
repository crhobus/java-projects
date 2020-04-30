package br.com.app.infra.response;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class RestResponse<T> {

    private Response<T> response;

    public RestResponse() {
        this.response = new Response<>();
    }

    public Optional<ResponseEntity<Response<T>>> verifyErrors(BindingResult result) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return Optional.of(ResponseEntity.badRequest().body(response));
        }
        return Optional.empty();
    }

    public ResponseEntity<Response<T>> success(T object) {
        response.setData(object);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<T>> error(String message) {
        response.getErrors().add(message);
        return ResponseEntity.badRequest().body(response);
    }

}
