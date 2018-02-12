package edu.furb.webapp;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Caio
 */
public class LoginForm extends ActionForm {

    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String error;

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            error = "";
        }
        if (getEmail() == null || getEmail().length() < 1) {
            errors.add("email", new ActionMessage("error.email.required"));
            error = "";
        }
        return errors;
    }

    public String getName() {
        return name;
    }

    public void setName(String string) {
        name = string;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError() {
        this.error = "<span style='color:red'>Please provide valid entries for both fields</span>";
    }
}
