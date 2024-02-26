<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Movie Details</title>
    </head>
    <body>
    <%
        com.ryerson.rentview.Helper.MovieInfo movie = (com.ryerson.rentview.Helper.MovieInfo) request.getAttribute("movie");
        if (movie != null) {
            String imagePath = movie.getMovieImagePath();
    %>
        <img src="<%= request.getContextPath() + imagePath %>" alt="Movie Image" width="270" height="400">
    <%
        }
    %>
    </body>
</html>
