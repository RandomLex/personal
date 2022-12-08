package com.barzykin.personal.app.aop;

import com.barzykin.personal.app.repositories.helpers.EntityManagerHelper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Aspect
@Component
@RequiredArgsConstructor
public class JpaTransactionAspect {
    private final EntityManagerHelper helper;

    @Around("@annotation(JpaTransaction)") //Advice type around
    public Object transaction(ProceedingJoinPoint jp) {
        EntityManager em = null;
        Object result = null;
        try {
            em = helper.getEntityManager();
            em.getTransaction().begin();

            result = jp.proceed();

            em.getTransaction().commit();
        } catch (Throwable e) {
            safeRollback(em);
        } finally {
            safeClose(em);
        }
        return result;
    }

    private static void safeRollback(EntityManager em) {
        EntityTransaction tx;
        if (em != null && (tx = em.getTransaction()) != null) {
            tx.rollback();
        }
    }

    private static void safeClose(EntityManager em) {
        if (em != null) {
            em.close();
        }
    }

}
