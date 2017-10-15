package org.calc.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTServiceAsync {

    public void resultado(String conta, AsyncCallback<String> asyncCallback);
}
