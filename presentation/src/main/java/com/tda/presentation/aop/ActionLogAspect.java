package com.tda.presentation.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.tda.model.dentist.DentistForm;

@Aspect
public class ActionLogAspect {

    @Pointcut("execution(* processSubmit(..))")
    private void processSubmit() {}

    @Pointcut("within(com.tda.presentation.controller.Edit*)")
    private void editController() {}

    @AfterReturning(pointcut="editController() && " +
    		"processSubmit() && args(dForm,..)",
            returning="retVal")
    public void logAfterEdit(JoinPoint jp, DentistForm dForm, Object retVal) {

        if ( !retVal.toString().contains("redirect") ) {
            return;
        }
        
        System.out.println("id: " + dForm.getId());
        
        /* The user has modified something, log it. */
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("===================================");
        System.out.println("calling: " + getName(jp) + 
                " with username: "+ user.getUsername() + 
                " with date: " + new Date() +
                " with params: " + getParams(jp)
        );
        System.out.println("===================================");
    }
    private String getName(JoinPoint jp) {
        return jp.getTarget().getClass().getCanonicalName();
    }
    private String getParams(JoinPoint jp) {
        StringBuilder sb = new StringBuilder();

        for (Object object : jp.getArgs()) {
            sb.append(object.toString()+", ");
        }

        return sb.toString();
    }
}
