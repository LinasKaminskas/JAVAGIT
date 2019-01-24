package lt.bta.java2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/abc/LoginProcessor")
public class LoginProcessorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");    // jeigu tekstas text/plain
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Bandymas</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "            <h1>Labas rytas diena </h1>\n" +
                "            <form name=\"loginForm\" method=\"get\" action=\"LoginProcessor\">\n" +
                "                Username:<br>\n" +
                "                <input type=\"text\" name=\"username\"/> <br/>\n" +
                "                Password:<br>\n" +
                "                <input type=\"password\" name=\"password\"/> <br/>\n" +
                "                <br><br>\n" +
                "                <input type=\"submit\" value=\"Login\" />\n" +
                "            </form>\n" +
                "</body>\n" +
                "</html>");
        PrintWriter writer = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username.equals("admin")&password.equals("admin")){ // Object.equals(username, "admin") && ... - reikia taip lyginti ant POST
            writer.println("<h2>Successful Login</h2>");
        } else writer.println("<h3> Error ! Login Failed </h3>");
    }
}
