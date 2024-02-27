<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.ryerson.rentview.Helper.MovieInfo"%>
<%@page import="com.ryerson.rentview.Persistence.Movie_CRUD"%>
<%@page import="com.ryerson.rentview.Business.RentalManager"%>
<%@page import="com.ryerson.rentview.Business.ReviewManager"%>
<%@page import="com.ryerson.rentview.Helper.MemberInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.ryerson.rentview.Helper.ReviewInfo"%>
<html>
    <head>
        <title>Movie Details</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <header>
            <nav>
                <form action="index.jsp">
                        <button type="submit">Home</button>
                </form>
            </nav>
        </header>
        <% 
            int movieID = Integer.parseInt(request.getParameter("movieID"));
            MovieInfo movie = Movie_CRUD.readMovie(movieID);
            if (movie != null) {
        %>
            <h1><%= movie.getMovieName() %></h1>
            <img src="<%= request.getContextPath() + movie.getMovieImagePath() %>" alt="<%= movie.getMovieName() %>" width="270" height="400">
            <p>Release Year: <%= movie.getReleaseYear() %></p>
            <p>Rental Cost: $<%= movie.getRentalCost() %></p>
            <% if (session.getAttribute("rentalStatus") != null && session.getAttribute("rentalStatus").equals("Success")) { %>
                <p style="color: green;">Rental successful!</p>
                <% session.removeAttribute("rentalStatus"); %>
            <% } %>
            <% if (session.getAttribute("memberInfo") != null) {
                MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
                RentalManager rentalManager = new RentalManager();
                boolean hasRented = rentalManager.authenticateRentalByMemberID(memberInfo.getMemberID(), movie.getMovieID());
                if (hasRented) {
            %>
                <button>Watch Now</button>
            <% } else { %>
                <form action="RentMovieServlet" method="post">
                    <input type="hidden" name="movieID" value="<%= movie.getMovieID() %>">
                    <button type="submit">Rent Now</button>
                </form>
            <% }
            } else { %>
                <a href="login.jsp?redirect=movie.jsp&movieID=<%= movie.getMovieID() %>"><button>Rent Now</button></a>
            <% } %>
            <form action="SubmitReviewServlet" method="post">
                <input type="hidden" name="movieID" value="<%= movie.getMovieID() %>">
                Rating: <select name="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select><br>
                Review: <textarea name="review" placeholder="Enter your review here..."></textarea><br>
                <button type="submit">Submit Review</button>
            </form>

        <% 
            } else {
        %>
            <p>Movie not found.</p>
        <% 
            }
        %>
        
        <% 
            List<ReviewInfo> reviews = ReviewManager.getReviewsByMovieID(movieID);
            if (reviews != null && !reviews.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>Rating</th>
                    <th>Review</th>
                </tr>
                <% for (ReviewInfo review : reviews) { %>
                    <tr>
                        <td><%= review.getRating() %></td>
                        <td><%= review.getReviewText() %></td>
                    </tr>
                <% } %>
            </table>
        <% } %>

        
        <footer>
            <nav>
                <button onclick="window.scrollTo(0, 0);">scroll to top</button>
                <a href="#terms">Terms & conditions</a>
                <a href="#about">About us</a>
                <a href="#support">Support</a>
            </nav>
        </footer>
    </body>
</html>