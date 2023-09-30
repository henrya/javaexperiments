package servlet;

import java.io.*;
import java.time.*;
import java.util.Random;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/get-thread")
public class GetThread extends HttpServlet {
    public static int MIN_SLEEP = 1000;
    public static int MAX_SLEEP = 2000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        Instant start = Instant.now();
        PrintWriter out = response.getWriter();
        Random random = new Random();
        // Delay the request between 1 to 2 seconds
        int delay = random.nextInt((MAX_SLEEP - MIN_SLEEP) + 1) + MIN_SLEEP;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
           out.printf("Failed with an exception: %s", ex.getMessage());
        }
        out.printf("Thread %s, Wait: %d, Start: %s, End: %s , ",Thread.currentThread().toString(), delay, start, Instant.now());
    }
}
