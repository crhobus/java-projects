package br.com.app.math;

import org.springframework.stereotype.Service;

@Service
public class SimpleMath {

    public Double sum(Double v1, Double v2) {
        return v1 + v2;
    }

    public Double subtraction(Double v1, Double v2) {
        return v1 - v2;
    }

    public Double multiplication(Double v1, Double v2) {
        return v1 * v2;
    }

    public Double division(Double v1, Double v2) {
        return v1 / v2;
    }

    public Double mean(Double v1, Double v2) {
        return (v1 + v2) / 2;
    }

    public Double squareRoot(Double v) {
        return (Double) Math.sqrt(v);
    }
}
