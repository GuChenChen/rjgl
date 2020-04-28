package com.fykj.scaffold.security.oauth2.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableAnalyzer;
import result.Result;
import result.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * spring security统一的异常解析器
 *
 * @author wangf
 */
public class ErrorAnalyzer {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    public void translate(HttpServletRequest request, HttpServletResponse response, Exception exception) throws Exception {

        int httpErrorCode = 400;
        Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain(exception);
        Exception ase = (InsufficientAuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
                causeChain);
        if (ase != null) {
            httpErrorCode =ResultCode.UNAUTHORIZED.code();
        }

        ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class,
                causeChain);
        if (ase != null) {
            httpErrorCode = HttpServletResponse.SC_FORBIDDEN;
        }

        //AuthenticationException

        //AccessDeniedException
        //HttpRequestMethodNotSupportedException

        //TODO 扩展别的异常
        Result result = new Result(httpErrorCode, ResultCode.get(httpErrorCode).message());
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(mapper.writeValueAsString(result));
    }
}
