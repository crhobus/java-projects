package org.calc.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.calc.client.service.GWTService;

public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    @Override
    public String resultado(String conta) {
        try {
            Double v1 = null;
            Double v2;
            Double result = null;
            String operador = "";
            String[] str = conta.split(" ");
            for (String s : str) {
                if (s.contains(",")) {
                    s = s.replace(",", ".");
                }
                if (v1 == null) {
                    v1 = Double.parseDouble(s);
                } else if ("+".equals(s)
                        || "-".equals(s)
                        || "*".equals(s)
                        || "/".equals(s)) {
                    operador = s;
                } else {
                    v2 = Double.parseDouble(s);
                    switch (operador) {
                        case "+":
                            if (result != null) {
                                result += v2;
                            } else {
                                result = v1 + v2;
                            }
                            break;
                        case "-":
                            if (result != null) {
                                result -= v2;
                            } else {
                                result = v1 - v2;
                            }
                            break;
                        case "*":
                            if (result != null) {
                                result *= v2;
                            } else {
                                result = v1 * v2;
                            }
                            break;
                        case "/":
                            if (result != null) {
                                result /= v2;
                            } else {
                                result = v1 / v2;
                            }
                            break;
                    }
                }
            }
            return Double.toString(result);
        } catch (Exception ex) {
            return "Error";
        }
    }
}
