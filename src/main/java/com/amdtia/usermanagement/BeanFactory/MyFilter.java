package com.amdtia.usermanagement.BeanFactory;

import com.amdtia.usermanagement.model.Permissions;
import com.amdtia.usermanagement.model.User;
import com.amdtia.usermanagement.repository.PermissionRepository;
import com.amdtia.usermanagement.repository.UserRepository;
import com.amdtia.usermanagement.security.SecurityConfig;
import com.amdtia.usermanagement.security.UserRestictionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class MyFilter implements Filter {


    @Autowired
    private UserRestictionProvider userRestictionProvider;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = (List) auth.getAuthorities();

            String url = ((HttpServletRequest)request).getRequestURI().toString();
//            System.out.println(url);


            HttpServletResponse res = (HttpServletResponse) response;
            chain.doFilter(request, res);

    }

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void destroy(){

    }
}
