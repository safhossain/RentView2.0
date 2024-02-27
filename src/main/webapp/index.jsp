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
                    for (int i = 0; i < movies.size(); i++) {
                        MovieInfo movie = movies.get(i);
                        if (movie.isMovieFeatured() == true){
            %>
                            <div class="movie-poster">
                                <a href="movie.jsp?movieID=<%= movie.getMovieID() %>">
                                    <img src="<%= request.getContextPath() + movie.getMovieImagePath() %>" alt="<%= movie.getMovieName() %>" width="270" height="400">
                                </a>
                            </div>
            <%          }
                    }
                }
            %>
        </section>
        
        <section id="scrolling-movies">
            <div class="scroll-container" id="auto-scroll">
                <% 
                    if (movies != null && movies.size() > 3) {
                        for (int i = 3; i < movies.size(); i++) {
                            MovieInfo movie = movies.get(i);
                            if (movie.isMovieFeatured() == false){
                %>
                                <div class="movie-poster">
                                <a href="movie.jsp?movieID=<%= movie.getMovieID() %>">
                                    <img src="<%= request.getContextPath() + movie.getMovieImagePath() %>" alt="<%= movie.getMovieName() %>" width="270" height="400">
                                </a>
                <%          }
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
            
        <script>
            const scrollContainer = document.getElementById('auto-scroll');
            let scrollAmount = 0;
            let scrollSpeed = 1;
            let direction = 1;
            let isHovering = false;

            function autoScroll() {
                if (!isHovering) {
                    if (scrollContainer.scrollWidth <= scrollContainer.clientWidth) {
                        return; // No need to scroll if the content fits in the container
                    }

                    scrollAmount += scrollSpeed * direction;

                    if (scrollAmount >= scrollContainer.scrollWidth - scrollContainer.clientWidth || scrollAmount <= 0) {
                        direction *= -1; // Change direction at the ends
                    }

                    scrollContainer.scrollLeft = scrollAmount;
                }
                requestAnimationFrame(autoScroll); // Continue the animation
            }

            scrollContainer.addEventListener('mouseenter', () => {
                isHovering = true;
            });

            scrollContainer.addEventListener('mouseleave', () => {
                isHovering = false;
            });

            autoScroll(); // Start the automatic scrolling
        </script>

    </body>
</html>
