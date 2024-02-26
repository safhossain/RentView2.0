package com.ryerson.rentview.GUI;

import com.ryerson.rentview.Helper.MovieInfo;
import com.ryerson.rentview.Persistence.Movie_CRUD;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* NOTE: THIS SERVLET IS MOSTLY FOR TESTING/DEBUGGING PURPOSES */

public class MovieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int movieId = 1; //testing with movieID 2
        MovieInfo movie = Movie_CRUD.readMovie(movieId);
        if (movie != null) {
            request.setAttribute("movie", movie);
        }
        request.getRequestDispatcher("/movie.jsp").forward(request, response);
    }
}
