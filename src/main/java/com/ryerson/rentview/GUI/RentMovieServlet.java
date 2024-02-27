package com.ryerson.rentview.GUI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ryerson.rentview.Business.RentalManager;
import com.ryerson.rentview.Helper.MemberInfo;

public class RentMovieServlet extends HttpServlet {    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        if (memberInfo != null) {
            int memberId = memberInfo.getMemberID();
            int movieId = Integer.parseInt(request.getParameter("movieID"));
            
            LocalDate rentalDate = LocalDate.now();
            LocalDate returnDate = rentalDate.plusDays(12);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            RentalManager.createRental(memberId, movieId, rentalDate.format(formatter), returnDate.format(formatter));
            session.setAttribute("rentalStatus", "Success");            
            response.sendRedirect("movie.jsp?movieID=" + movieId);
        } else {
            // Redirect to login page if not logged in
            response.sendRedirect("login.jsp");
        }
    }
}
