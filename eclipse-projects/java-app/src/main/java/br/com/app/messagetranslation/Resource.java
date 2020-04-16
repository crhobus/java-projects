package br.com.app.messagetranslation;

import java.util.Locale;
import java.util.ResourceBundle;

public class Resource {

    private ResourceBundle bundle;

    public static Resource resourceBundle(String locale) {
        Resource resource = new Resource();
        resource.bundle = ResourceBundle.getBundle("translations/messages", Locale.forLanguageTag(locale));
        return resource;
    }

    public String getResource(String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        System.err.println("A chave informada não existe");
        return "";
    }
}
