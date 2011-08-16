package com.tda.presentation.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

@Component
public class RedirectingExceptionResolver implements HandlerExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UrlPathHelper pathHelper = new UrlPathHelper();

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");

        String lookupPath = pathHelper.getLookupPathForRequest(request);
        String errorRef = RandomStringUtils.randomAlphanumeric(7).toUpperCase();

        System.out.println("Unexpected error [" + errorRef + "] for path [" + lookupPath + "]: " + ex);
        logger.error("Unexpected error [" + errorRef + "] for path [" + lookupPath + "]: " + ex, ex);

        return modelAndView;
    }
}
