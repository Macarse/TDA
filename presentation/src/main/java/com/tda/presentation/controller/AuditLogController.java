package com.tda.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tda.model.audit.AuditLog;
import com.tda.persistence.paginator.Paginator;
import com.tda.presentation.params.ParamContainer;
import com.tda.service.api.AuditLogService;

@Controller
@RequestMapping(value = "/auditLog")
public class AuditLogController extends CommonController{
    private static final String AUDIT_LOG_LIST = "auditLog/list";

    private AuditLogService auditLogService;
    private Paginator paginator;
    private ParamContainer params;

    public AuditLogController() {
        params = new ParamContainer();
    }

    @Autowired
    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @Autowired
    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
        paginator.setOrderAscending(true);
        paginator.setOrderField("id");
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView search(
            @ModelAttribute AuditLog auditLog,
            BindingResult result,
            @RequestParam(value = "page", required = false) Integer pageNumber,
            @RequestParam(value = "orderField", required = false) String orderField,
            @RequestParam(value = "orderAscending", required = false) Boolean orderAscending){
        
        ModelAndView modelAndView = new ModelAndView(AUDIT_LOG_LIST);
        
        //set first page paginator
        paginator.setPageIndex(1);

        if ( auditLog.getFormId() != null ) {
            params.setParam("formId", auditLog.getFormId().toString());
        }

        if ( auditLog.getUser() != null ) {
            params.setParam("user", auditLog.getUser());
        }

        if ( auditLog.getControllerUsed() != null ) {
            params.setParam("controllerUsed", auditLog.getControllerUsed());
        }

        if ( auditLog.getTimestamp() != null ) {
            params.setParam("timestamp", auditLog.getTimestamp().toString());
        }


        modelAndView = processRequest(modelAndView, auditLog, pageNumber, orderField, orderAscending);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getList(
            @RequestParam(value = "page", required = false) Integer pageNumber,
            @RequestParam(value = "orderField", required = false) String orderField,
            @RequestParam(value = "orderAscending", required = false) Boolean orderAscending) {
        ModelAndView modelAndView = new ModelAndView(AUDIT_LOG_LIST);

        modelAndView = processRequest(modelAndView, new AuditLog(), pageNumber, orderField, orderAscending);

        return modelAndView;
    }

    private ModelAndView processRequest(ModelAndView modelAndView, 
            AuditLog auditLog, Integer pageNumber, String orderField,
            Boolean orderAscending){

        List<AuditLog> auditLogList = null;
        
        // Pagination
        if (pageNumber != null) {
            paginator.setPageIndex(pageNumber);
        }

        // Order
        if (orderField == null || orderAscending == null) {
            orderField = "id";
            orderAscending = true;
        }

        paginator.setOrderAscending(orderAscending);
        paginator.setOrderField(orderField);

        auditLogList = auditLogService.findByExamplePaged(auditLog, paginator);

        modelAndView.addObject("auditLog", new AuditLog());
        modelAndView.addObject("auditLogList", auditLogList);
        modelAndView.addObject("paginator", paginator);
        modelAndView.addObject("params", params);
        modelAndView.addObject("orderField", orderField);
        modelAndView.addObject("orderAscending", orderAscending.toString());
        
        return modelAndView;
    }
}
