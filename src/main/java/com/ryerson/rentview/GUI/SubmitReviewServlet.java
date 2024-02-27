package com.ryerson.rentview.GUI;

import com.ryerson.rentview.Business.ReviewManager;
import com.ryerson.rentview.Helper.MemberInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SubmitReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        if (memberInfo != null) {
            int memberID = memberInfo.getMemberID();
            int movieID = Integer.parseInt(request.getParameter("movieID"));
            String reviewText = request.getParameter("review");
            int rating = Integer.parseInt(request.getParameter("rating"));

            ReviewManager.createReview(reviewText, rating, memberID, movieID);

            response.sendRedirect("movie.jsp?movieID=" + movieID);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
