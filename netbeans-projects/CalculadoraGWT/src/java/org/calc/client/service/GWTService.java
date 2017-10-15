package org.calc.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service/gwtService")
public interface GWTService extends RemoteService {

    public String resultado(String conta);
}
