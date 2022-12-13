import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.RequestDispatcher;
public class Register extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter p = response.getWriter();
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String mail = request.getParameter("email");
        String password = null;
        int valid = 0;
        try {
            valid = 0;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from users");
            int subjectcount = 0;
            while (r.next()) {
                // p.println("Entered " + r.getString("name"));
                subjectcount = r.getInt("subjectcount");
                if (r.getString("name").equals(name)) {

                    //p.println("Exists");
                    //response.sendRedirect("/IT-Department/signin.html");
                    valid = 1;
                }
            }
            p.println(subjectcount);
            if (valid == 0) {
                Float val=0f;
                String sql = "insert into users values('" + name + "','" + pass + "','" + mail + "'," + val+","+subjectcount;
                for (int i = 0; i < subjectcount; i++) {
                    sql += ",''";
                }
                sql += ")";
                p.println(sql);
                PreparedStatement ps = c.prepareStatement(sql);
                // ps.setString(1, name);
                // ps.setString(2, pass);
                // ps.setString(3, mail);
                // ps.setFloat(4, 0);
                // p.println(ps.toString());
                int i = ps.executeUpdate();
                if (i > 0) {
                    s.executeQuery("commit");
                    Cookie ck = new Cookie("loggedinuser", name);
                    response.addCookie(ck);
                    response.sendRedirect("http://localhost:8085/IT-Department/mainpage");
                }
            }
            else {
                p.println("<script>confirm('Username already taken');</script>");
                response.setHeader("refresh", "1;url=http://localhost:8085/IT-Department/signin.html");
            }
            r.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }
}