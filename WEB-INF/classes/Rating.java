
import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Rating extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter p = response.getWriter();
        String year = request.getParameter("year");
        String subject = request.getParameter("subject");
        String section = request.getParameter("section");
        // String mail = request.getParameter("email");
        int presentval = Integer.parseInt(request.getParameter("present"));
        int stars = Integer.parseInt(request.getParameter("stars"+presentval));
        Cookie ck[] = request.getCookies();
        // p.println(name);
        if (ck != null) {
            String user = ck[0].getValue();
            try {
                String count;
                int co = 0;
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection c = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
                Statement s = c.createStatement();
                // DatabaseMetaData md = c.getMetaData();
                String table="users";
                String column = subject + "rating"+year+section;
                column = column.replaceAll("\\s", "");
                column = column.toLowerCase();
 
        // System.out.println(str);        
                p.println(table+" "+column);
                String sql = "update users set " + column + "=" + stars + " where name='" + user + "'";
                p.println(sql);
                    PreparedStatement ps = c.prepareStatement(sql);
                    int i=ps.executeUpdate();
                // p.println(stars);
                // ResultSet r = s.executeQuery("select count from it" + request.getParameter("year")
                // + " where section='" + request.getParameter("section") + "'");
                // while (r.next()) {
                // p.println("Entered " + r.getString("name"));
                // count = r.getString("count");
                // co = Integer.parseInt(count);
                // p.println("Exists");
                // response.sendRedirect("/IT-Department/signin.html");
                // }
                //     String sql = "update it" + year + " set count='" + (co + 1) + "' where section='" + section + "'";
                //     PreparedStatement ps = c.prepareStatement(sql);
                //     DatabaseMetaData md = c.getMetaData();
                //     p.println("came");
                //     String column = "FACULTY"+(co+1);
                //     ResultSet rs = md.getColumns(null, null, table, column);
                //     if (rs.next()) {
                //     } else {
                //         String sql2 = "alter table it" + year + " add(faculty" + (co + 1)
                //                 + " varchar2(1000),subject" + (co + 1) + " varchar2(1000))";
                //         PreparedStatement ps2 = c.prepareStatement(sql2);
                //         int j = ps2.executeUpdate();
                //     }
                //     String sql3 = "update it" + year + " set faculty" + (co + 1) + "='" + name + "',subject"
                //                 + (co + 1) + "='" + subject + "' where section='" + section + "'";
                //         PreparedStatement ps3 = c.prepareStatement(sql3);
                //         int k = ps3.executeUpdate();
                //     // p.println(sql2);
                //     // p.println(sql3);
                //     // ps.setFloat(4, 0);
                //     // p.println(ps.toString());
                //     int i = ps.executeUpdate();
                //     // p.println(i);
                    s.executeQuery("commit");
                    response.sendRedirect(
                            "http://localhost:8085/IT-Department/faculty?year=" + year + "&section=" + section + "");
                    // ps.close();
                    s.close();
                    c.close();
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
        }else {
            response.setHeader("refresh", "1;url=http://localhost:8085/IT-Department/index.html");
    }
    }
}