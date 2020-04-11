package br.com.app.auditoria;

import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import br.com.app.auditoria.dao.AuditoriaEntity;
import br.com.app.auditoria.dao.AuditoriaRepository;
import br.com.app.auditoria.dto.StatusEnum;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    private final Gson gson = new Gson();

    @Transactional
    public Object salvar(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String eventPayload = gson.toJson(args);
        String methodName = joinPoint.getSignature().getName();

        AuditoriaEntity auditoria = new AuditoriaEntity();
        auditoria.setData(Instant.now());
        auditoria.setApi(methodName);
        auditoria.setInputJson(eventPayload);

        try {
            Object responsePayload = joinPoint.proceed();
            auditoria.setOutputJson(gson.toJson(responsePayload));
            auditoria.setStatus(StatusEnum.SUCESSO);
            repository.save(auditoria);
            return responsePayload;
        } catch (Throwable throwable) {
            auditoria.setOutputJson(gson.toJson(throwable.getMessage()));
            auditoria.setStatus(StatusEnum.ERRO);
            repository.save(auditoria);
            throw throwable;
        }
    }
}
