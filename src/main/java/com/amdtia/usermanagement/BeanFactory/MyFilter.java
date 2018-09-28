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

        String url = ((HttpServletRequest)request).getRequestURI().toString();

//if findURL returns true then the program allows user to access that url if not then it redirects them to the error page
        HttpServletResponse res = (HttpServletResponse) response;
        if(url.equals("/loginPage") || url.equals("/register")){
            chain.doFilter(request, res);
        }
        else{
            if(userRestictionProvider.findURL(url)==true){
                chain.doFilter(request, res);
            }
            else{
                res.setContentType("showError");
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request rejected an attempt to run commands was detected.");
            }
        }




    }

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void destroy(){

    }
}
