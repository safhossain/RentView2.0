<% 
    if (request.getAttribute("movies") == null) {
        response.sendRedirect("IndexServlet");
        return;
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.ryerson.rentview.Helper.MemberInfo"%>
<%@page import="com.ryerson.rentview.Helper.MovieInfo"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>RentView</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
    </head>    
    <body>
        <header>
            <nav>
                <form action="index.jsp">
                    <button type="submit">Home</button>
                </form>
                <button id="genres">Genres</button>
                <input type="search" id="movie-search" placeholder="Search...">
                <% 
                    MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
                    if (memberInfo != null) {
                %>
                    <span>Welcome, <%= memberInfo.getFirstName() %></span>
                    <button id="profile">Profile</button>
                    <form action="logout.jsp">
                        <button type="submit">Logout</button>
                    </form>
                    <% if (memberInfo.getMemberType().equals("manager")){ %>
                        <form action="UserManagementServlet" method="GET">
                            <button type="submit">ADMIN TOOLS</button>
                        </form>
                    <% } %>
                    
                <% } else { %>
                    <form action="login.jsp">
                        <button type="submit">Login</button>
                    </form>
                <% } %>
            </nav>
        </header>
        <section id="featured">
            <% 
                List<MovieInfo> movies = (List<MovieInfo>) request.getAttribute("movies");
                if (movies != null && !movies.isEmpty()) {
                    for (int i = 0; i < 3 && i < movies.size(); i++) {
                        MovieInfo movie = movies.get(i);
            %>
                        <div class="movie-poster">
                            <img src="<%= request.getContextPath() + movie.getMovieImagePath() %>" alt="<%= movie.getMovieName() %>" width="270" height="400">
                        </div>
            <% 
                    }
                }
            %>
        </section>
        
        <section id="scrolling-movies">
            <div class="scroll-container">
                <% 
                    if (movies != null && movies.size() > 3) {
                        for (int i = 3; i < movies.size(); i++) {
                            MovieInfo movie = movies.get(i);
                %>
                            <div class="movie-poster-small">
                                <img src="<%= request.getContextPath() + movie.getMovieImagePath() %>" alt="<%= movie.getMovieName() %>" width="135" height="200">
                            </div>
                <% 
                        }
                    }
                %>
            </div>
        </section>
        
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
