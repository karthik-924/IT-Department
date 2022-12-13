
import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Addfaculty extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter p = response.getWriter();
        // String mail = request.getParameter("email");
        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String year = request.getParameter("year");
        String section = request.getParameter("section");
        String table = "IT" + year;
        
        int valid = 0;
        Cookie ck[] = request.getCookies();
        // p.println(name);
        // p.println((ck[0].getValue()).equals(name));
        if (ck != null) {
            try {
                valid = 0;
                String count;
                int co = 0;
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection c = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
                Statement s = c.createStatement();
                String sqlr="select count from it" + year
                        + " where section='" + section + "'";
                p.println(sqlr);
                ResultSet r = s.executeQuery(sqlr);
                while (r.next()) {
                    // p.println("Entered " + r.getString("name"));
                    count = r.getString("count");
                    co = Integer.parseInt(count);
                    // p.println("Exists");
                    // response.sendRedirect("/IT-Department/signin.html");
                }
                String sql = "update it" + year + " set count='" + (co + 1) + "' where section='" + section + "'";
                PreparedStatement ps = c.prepareStatement(sql);
                DatabaseMetaData md = c.getMetaData();
                int i = ps.executeUpdate();
                String column = "FACULTY" + (co + 1);
                ResultSet rs = md.getColumns(null, null, table, column);
                if (rs.next()) {
                } else {
                    String sql2 = "alter table it" + year + " add(faculty" + (co + 1)
                            + " varchar2(1000),subject" + (co + 1) + " varchar2(1000))";
                            p.println(sql2);
                    PreparedStatement ps2 = c.prepareStatement(sql2);
                    int j = ps2.executeUpdate();
                }
                String sql3 = "update it" + year + " set faculty" + (co + 1) + "='" + name + "',subject"
                        + (co + 1) + "='" + subject + "' where section='" + section + "'";
                        p.println(sql3);
                PreparedStatement ps3 = c.prepareStatement(sql3);
                int k = ps3.executeUpdate();
                column = subject + "rating"+year+section;
                column = column.replaceAll("\\s", "");
                column = column.toLowerCase();
                String sql4 = "alter table users add " + column + " float";
                p.println(sql4);
                PreparedStatement ps4 = c.prepareStatement(sql4);
                int l = ps4.executeUpdate();
                Statement sub = c.createStatement();
                ResultSet subco = sub.executeQuery("select * from users");
                int subjectcount = 0;
                
                while (subco.next()) {
                    // p.println(subjectcount);
                    subjectcount = subco.getInt("subjectcount");
                }
                
                String sql5 = "update users set subjectcount=" + (subjectcount+1);
                PreparedStatement ps5 = c.prepareStatement(sql5);
                int m = ps5.executeUpdate();
                // p.println(sql2);
                // p.println(sql3);
                // ps.setFloat(4, 0);
                // p.println(ps.toString());
                
                // p.println(i);
                s.executeQuery("commit");
                response.sendRedirect(
                        "http://localhost:8085/IT-Department/faculty?year=" + year + "&section=" + section + "");
                ps.close();
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