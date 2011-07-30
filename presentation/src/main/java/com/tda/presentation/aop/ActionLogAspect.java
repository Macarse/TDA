package com.tda.presentation.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.tda.model.audit.AuditLog;
import com.tda.model.dentist.DentistForm;
import com.tda.model.nurse.NurseForm;
import com.tda.model.pediatrician.PediatricianForm;
import com.tda.model.socialworker.SocialWorkerForm;
import com.tda.service.api.AuditLogService;

@Aspect
public class ActionLogAspect {

    private AuditLogService auditLogService;

    @Autowired
    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @SuppressWarnings("unused")
    @Pointcut("execution(* processSubmit(..))")
    private void processSubmit() {
    }

    @SuppressWarnings("unused")
    @Pointcut("within(com.tda.presentation.controller.Edit*)")
    private void editController() {
    }

    @SuppressWarnings("unused")
    @Pointcut("within(com.tda.presentation.controller.Add*)")
    private void addController() {
    }

    // Log DentistForm edit
    @AfterReturning(pointcut = "editController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterEdit(JoinPoint jp, DentistForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }

        auditToDB(jp, form.getId());
    }

    // Log NurseForm edit
    @AfterReturning(pointcut = "editController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterEdit(JoinPoint jp, NurseForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }

        auditToDB(jp, form.getId());
    }

    // Log PediatricianForm edit
    @AfterReturning(pointcut = "editController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterEdit(JoinPoint jp, PediatricianForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }

        auditToDB(jp, form.getId());
    }

    // Log SocialWorkerForm edit
    @AfterReturning(pointcut = "editController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterEdit(JoinPoint jp, SocialWorkerForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }

        auditToDB(jp, form.getId());
    }

    // Log DentistForm add
    @AfterReturning(pointcut = "addController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterAdd(JoinPoint jp, DentistForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }
        auditToDB(jp, form.getId());
    }

    // Log NurseForm add
    @AfterReturning(pointcut = "addController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterAdd(JoinPoint jp, NurseForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }

        auditToDB(jp, form.getId());
    }

    // Log PediatricianForm add
    @AfterReturning(pointcut = "addController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterAdd(JoinPoint jp, PediatricianForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }

        auditToDB(jp, form.getId());
    }

    // Log SocialWorkerForm add
    @AfterReturning(pointcut = "addController() && "
            + "processSubmit() && args(form,..)", returning = "retVal")
    public void logAfterAdd(JoinPoint jp, SocialWorkerForm form, Object retVal) {

        if (!isCorrect(retVal.toString())) {
            return;
        }

        auditToDB(jp, form.getId());
    }

    private boolean isCorrect(String retVal) {
        // If it's not saved correctly, avoid log.
        if (!retVal.contains("redirect")) {
            return false;
        } else {
            return true;
        }
    }

    private void auditToDB(JoinPoint jp, Long formId) {
        /* The user has modified something, log it. */
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String controllerUsed = getName(jp);

        AuditLog auditLog = new AuditLog(formId, user.getUsername(),
                controllerUsed, new Date());
        auditLogService.save(auditLog);
    }

    private String getName(JoinPoint jp) {
        return jp.getTarget().getClass().getCanonicalName();
    }
}
