package com.mitocode.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*14.1.1 Creación de proyecto frontend y consumo de API REST :: Por defecto, una aplicacion backend en spring boot solo permite
 * ser consumida por ella misma y no por otra aplicacion desde otro puerto. Para que sí sea consumida por otra aplicacion desde
 * cualquier para cualquier controlador de este backend, implementamos esta clase. Esta clase tiene que ser un bean, por eso ponemos la anotacion @Component:*/
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORS implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
/*14.1.2 Creación de proyecto frontend y consumo de API REST :: Este metodo es para establecer que los controladores http del backend pueden ser consumidos desde cualquier
* url (incluyendo puerto).*/
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, PUT");
        httpResponse.setHeader("Access-Control-Max-Age", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, ContentType, Authorization");

        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) httpResponse.setStatus(HttpServletResponse.SC_OK);
        else filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
