package br.com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.converter.NumberConverter;
import br.com.app.exception.UnsuportedMathOperationException;
import br.com.app.math.SimpleMath;

@RestController
@RequestMapping("/api.com/calculadora")
public class MathController {

    @Autowired
    private SimpleMath math;

    @GetMapping(value = "/soma/{v1}/{v2}")
    public Double soma(@PathVariable("v1") String v1, @PathVariable("v2") String v2) throws Exception {
        if (!NumberConverter.isNumeric(v1) || !NumberConverter.isNumeric(v2)) {
            throw new UnsuportedMathOperationException("Por favor, defina um valor numérico!");
        }
        return math.sum(NumberConverter.covertToDouble(v1), NumberConverter.covertToDouble(v2));
    }

    @GetMapping(value = "/subtracao/{v1}/{v2}")
    public Double subtracao(@PathVariable("v1") String v1, @PathVariable("v2") String v2) throws Exception {
        if (!NumberConverter.isNumeric(v1) || !NumberConverter.isNumeric(v2)) {
            throw new UnsuportedMathOperationException("Por favor, defina um valor numérico!");
        }
        return math.subtraction(NumberConverter.covertToDouble(v1), NumberConverter.covertToDouble(v2));
    }

    @GetMapping(value = "/multiplicacao/{v1}/{v2}")
    public Double multiplicacao(@PathVariable("v1") String v1, @PathVariable("v2") String v2) throws Exception {
        if (!NumberConverter.isNumeric(v1) || !NumberConverter.isNumeric(v2)) {
            throw new UnsuportedMathOperationException("Por favor, defina um valor numérico!");
        }
        return math.multiplication(NumberConverter.covertToDouble(v1), NumberConverter.covertToDouble(v2));
    }

    @GetMapping(value = "/divisao/{v1}/{v2}")
    public Double divisao(@PathVariable("v1") String v1, @PathVariable("v2") String v2) throws Exception {
        if (!NumberConverter.isNumeric(v1) || !NumberConverter.isNumeric(v2)) {
            throw new UnsuportedMathOperationException("Por favor, defina um valor numérico!");
        }
        return math.division(NumberConverter.covertToDouble(v1), NumberConverter.covertToDouble(v2));
    }

    @GetMapping(value = "/media/{v1}/{v2}")
    public Double media(@PathVariable("v1") String v1, @PathVariable("v2") String v2) throws Exception {
        if (!NumberConverter.isNumeric(v1) || !NumberConverter.isNumeric(v2)) {
            throw new UnsuportedMathOperationException("Por favor, defina um valor numérico!");
        }
        return math.mean(NumberConverter.covertToDouble(v1), NumberConverter.covertToDouble(v2));
    }

    @GetMapping(value = "/raizQuadrada/{v}")
    public Double raizQuadrada(@PathVariable("v") String v) throws Exception {
        if (!NumberConverter.isNumeric(v)) {
            throw new UnsuportedMathOperationException("Por favor, defina um valor numérico!");
        }
        return math.squareRoot(NumberConverter.covertToDouble(v));
    }
}
