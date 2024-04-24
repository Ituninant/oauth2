package com.itanton;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

/**
 * @author itanton
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("====================");
        System.out.println("====================");
        System.out.println("====================");
        System.out.println("====================");
        System.out.println(request.getRequestURI() + " " + request.getMethod());
        System.out.println("Params:");
        request.getParameterMap().forEach((k, v) -> {
            System.out.print(k + ": ");
            System.out.print(String.join(" ", v));
            System.out.println();
        });
        System.out.println("Headers:");
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                System.out.println("Header " + headerName + ": " + request.getHeader(headerName));
            }
        }
        filterChain.doFilter(request, response);
    }
}
