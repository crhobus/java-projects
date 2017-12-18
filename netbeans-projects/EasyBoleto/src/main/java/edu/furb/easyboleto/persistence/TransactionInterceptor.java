package edu.furb.easyboleto.persistence;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transaction
public class TransactionInterceptor {

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        try {
            manager.getTransaction().begin();
            Object obj = context.proceed();
            manager.getTransaction().commit();
            return obj;
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw ex;
        }
    }
}
