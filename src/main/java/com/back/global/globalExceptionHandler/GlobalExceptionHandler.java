package com.back.global.globalExceptionHandler;

import com.back.global.exception.ServiceException;
import com.back.global.rsData.RsData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public RsData<Void> handle(ServiceException ex, HttpServletResponse reponse) {
        RsData<Void> rsData = ex.getRsData();
        reponse.setStatus(rsData.statusCode());

        return rsData;
    }
}