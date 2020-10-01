package br.com.app.javascriptnashorn;

import java.io.FileReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/*
 * Executa código JavaScript via Java.
 */
public class Main {

    public static void main(String[] args) {
        ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");

        Bindings bind = ee.getBindings(ScriptContext.ENGINE_SCOPE);

        bind.put("goodbye", " Até logo!!!");

        try {
            ee.eval(new FileReader("olamundo.js"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
