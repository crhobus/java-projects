package br.com.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import br.com.app.logger.AppLogger;

@Aspect
@Configuration
public class ProdutoAspect {

    @Before("service()")
    public void before(JoinPoint joinPoint) {
        AppLogger.LOGGER.info("Será executado o seguinte ponto: {}", joinPoint);
    }

    @After("service()")
    public void after(JoinPoint joinPoint) {
        AppLogger.LOGGER.info("Executou o seguinte ponto: {}", joinPoint);
    }

    @AfterReturning(pointcut = "service()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        AppLogger.LOGGER.info("O ponto {} retornou o seguinte valor: {}", joinPoint, result);
    }

    @AfterThrowing(pointcut = "service()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        AppLogger.LOGGER.info("O ponto {} retornou o seguinte erro: {}", joinPoint, error.getMessage());
    }

    @Around("@annotation(TempoExecucao)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object response = joinPoint.proceed();

        long tempo = System.currentTimeMillis() - start;
        AppLogger.LOGGER.info("A execução do ponto {} demorou {} ms", joinPoint, tempo);

        return response;
    }

    @Pointcut("execution(* br.com.app.service.*.*(..))")
    private void service() {}

}
