package com.centric.bench.exceptions;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.centric.bench.custom.exception.EmptyInputException;
import com.centric.bench.dto.ResponseDTO;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class GraphQLResolverAspect {

    @Around("@annotation(io.leangen.graphql.annotations.GraphQLMutation) || @annotation(io.leangen.graphql.annotations.GraphQLQuery)")
    public Object wrapException(ProceedingJoinPoint jp) throws Throwable {
    	Object result = null;
        Class<?> retClass = ((MethodSignature) jp.getSignature()).getReturnType();
        try {
        	result= jp.proceed();
        	
        }catch(EmptyInputException ex) {
        	result = "GraphQL Resolver Exception Handled";
        }catch(Exception ex) {
        	result = "Internal Server Error";
        }finally {
        	try {
                addResponseCodeToHeaders(result);
            } catch (Exception ignore) {
            }
        }
       
        return result;
    }
    
    
    private void addResponseCodeToHeaders(Object result) {
        if (Objects.nonNull(result) && result instanceof ResponseDTO) {
            int statusCode = ((ResponseDTO<?>) result).getResponseCode();
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes instanceof ServletRequestAttributes) {
                HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
                if (Objects.nonNull(response)) {
                    response.setHeader("x-statusCode", String.valueOf(statusCode));
                }
            }
        }
    }
    
}
    
    
