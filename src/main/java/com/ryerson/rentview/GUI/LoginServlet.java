package com.ryerson.rentview.GUI;

import com.ryerson.rentview.Business.MemberManager;
import com.ryerson.rentview.Helper.MemberInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        MemberInfo memberInfo = MemberManager.authenticateMember(email, password);
        
        if (memberInfo != null) {
            HttpSession session = request.getSession();
            session.setAttribute("memberInfo", memberInfo);
            String redirect = request.getParameter("redirect");
            String movieID = request.getParameter("movieID");
            if ("manager".equals(memberInfo.getMemberType())) {
                response.sendRedirect("ManagerServlet");
            } else if (redirect != null && !redirect.isEmpty()) {
                if (movieID != null && !movieID.isEmpty()) {
                    response.sendRedirect(redirect + "?movieID=" + movieID);
                } else {
                    response.sendRedirect(redirect);
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }

    @Override
    public String getServletInfo() {
        return "Login servlet";
    }
}