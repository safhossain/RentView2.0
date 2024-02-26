package com.ryerson.rentview.GUI;

import com.ryerson.rentview.Helper.MemberInfo;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
            if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect("index.jsp");
            }
        } else {
            httpResponse.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}
