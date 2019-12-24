package ru.moskalevms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "UTF8Filter", urlPatterns = "/*")
public class UTF8Filter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
       /* // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        // Set the default response content type and encoding
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        next.doFilter(request, response);*/

            filterConfig.getServletContext().getRequestDispatcher("/encoding.jsp").include(request, response);
            next.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

}
