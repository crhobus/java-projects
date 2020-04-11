package br.com.app.infra.eventauditing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.app.auditoria.AuditoriaService;

@Aspect
@Component
public class AuditedEventAspect {

    @Autowired
    private AuditoriaService auditoriaService;

    @Around("@annotation(AuditedEvent)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return auditoriaService.salvar(joinPoint);
    }
}
