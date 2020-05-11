package main.java.servlet;

import main.java.model.User;
import main.java.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/admin.jsp"})
public class TheFilter implements Filter {
    FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc = filterConfig;
    }

    @Override
    public void destroy() {
        fc = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("role");
        if(user == null){
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        if (user.getRole().equalsIgnoreCase("admin")) {
            chain.doFilter(request, response);
        }else {
            request.getServletContext().getRequestDispatcher("/user").forward(request,response);
        }
    }
}

