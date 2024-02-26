package com.ryerson.rentview.GUI;

import com.ryerson.rentview.Business.MemberManager;
import com.ryerson.rentview.Business.MovieManagement;
import com.ryerson.rentview.Helper.MemberInfo;
import com.ryerson.rentview.Helper.MovieInfo;
import com.ryerson.rentview.Persistence.Movie_CRUD;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
            List<MemberInfo> users = MemberManager.getAllMembers();
            List<MovieInfo> movies = Movie_CRUD.getAllMovies();
            request.setAttribute("users", users);
            request.setAttribute("movies", movies);
            request.getRequestDispatcher("manager.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle any POST requests if necessary
    }

    @Override
    public String getServletInfo() {
        return "Manager servlet";
    }
}
