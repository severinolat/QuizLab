package edu.miu.cs;


import models.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QuizServlet", urlPatterns = {"/start"})
public class QuizServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Quiz quiz;
        if(session.getAttribute("quiz") ==  null){
            quiz =  new Quiz();
            session.setAttribute("quiz",quiz);
        }else{
            String answer = req.getParameter("answer");
            quiz = (Quiz)session.getAttribute("quiz");
            quiz.checkAnswer(answer);
            session.setAttribute("quiz", quiz);
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String question = quiz.getNextQuestion();
        out.println("<h1>The Number Quiz</h1>");
        out.println("<p>Your currrent score: </p>" + quiz.getScore());
        if(question == null){
            out.println("<p>You have complete the quiz, with a score "+quiz.getScore()+" out of "+quiz.getTotalScore()+"</p>");
        }else {

            out.println("<p>Guess the next number in the sequence.</p>");
            out.println("<p>" + question + "</p>");
            out.println("<form action=\"start\" method=\"post\">\n" +
                    "    Your answer is:\n" +
                    "    <input type=\"number\" name=\"answer\"/>\n" +
                    "    <input type=\"submit\" value=\"Submit\"/>\n" +
                    "</form>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
