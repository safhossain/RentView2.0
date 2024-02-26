package com.ryerson.rentview.GUI;

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

public class MovieManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
            List<MovieInfo> movies = Movie_CRUD.getAllMovies();
            request.setAttribute("movies", movies);
            request.getRequestDispatcher("manager.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        if (memberInfo != null && "manager".equals(memberInfo.getMemberType())) {
            if ("add".equals(action)) {
                // Retrieve movie details from the request
                String movieName = request.getParameter("movieName");
                int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
                double rentalCost = Double.parseDouble(request.getParameter("rentalCost"));
                String movieImagePath = request.getParameter("movieImagePath");
                boolean isMovieFeatured = Boolean.parseBoolean(request.getParameter("isMovieFeatured"));
                String directorFirstName = request.getParameter("directorFirstName");
                String directorLastName = request.getParameter("directorLastName");
                String genre1 = request.getParameter("genre1");
                String genre2 = request.getParameter("genre2");

                // Add the new movie
                MovieManagement.createMovie(movieName, releaseYear, rentalCost, movieImagePath, isMovieFeatured,
                                            directorFirstName, directorLastName, genre1, genre2);
            }
            // Redirect back to the manager page to see the updated movie list
            response.sendRedirect("MovieManagementServlet");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Movie Management Servlet";
    }
}
