import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.*;

public class Validate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter p = response.getWriter();
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String password = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from users");
            while (r.next()) {
                if (r.getString("name").equals(name) && r.getString("password").equals(pass)) {
                    Cookie ck = new Cookie("loggedinuser", name);
                    response.addCookie(ck);
                    response.sendRedirect("http://localhost:8085/IT-Department/mainpage");
                }
            }
            p.println("<script>alert('Invalid username or password');</script>");
            response.setHeader("refresh", "1;url=http://localhost:8085/IT-Department/index.html");
            r.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }
}