
import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Addsection extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter p = response.getWriter();
        // String mail = request.getParameter("email");
        String name = request.getParameter("name");
        String sectionf = request.getParameter("sectionf");
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
                int coun = 0;
                int maxcount = 0;
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection c = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
                Statement s = c.createStatement();
                ResultSet r = s.executeQuery("select * from it" + request.getParameter("year"));
                while (r.next()) {
                    // p.println("Entered " + r.getString("name"));
                    count = r.getString("count");
                    coun = Integer.parseInt(count);
                    if (coun > maxcount)
                        maxcount = coun;
                    co = co + 1;
                    // p.println("Exists");
                    // response.sendRedirect("/IT-Department/signin.html");
                }
                String sql = "insert into it" + year + " values(" + (co + 1) + ",'" + sectionf + "','0'";
                p.println(maxcount);
                for (int i = 1; i <= maxcount * 2; i++)
                    sql += ",''";
                sql += ")";
                p.println(sql);
                PreparedStatement ps = c.prepareStatement(sql);
                // DatabaseMetaData md = c.getMetaData();
                // p.println("came");
                // String column = "FACULTY"+(co+1);
                // ResultSet rs = md.getColumns(null, null, table, column);
                // if (rs.next()) {
                // } else {
                //     String sql2 = "alter table it" + year + " add(faculty" + (co + 1)
                //             + " varchar2(1000),subject" + (co + 1) + " varchar2(1000))";
                //     PreparedStatement ps2 = c.prepareStatement(sql2);
                //     int j = ps2.executeUpdate();
                // }
                // String sql3 = "update it" + year + " set faculty" + (co + 1) + "='" + name + "',subject"
                //             + (co + 1) + "='" + subject + "' where section='" + section + "'";
                //     PreparedStatement ps3 = c.prepareStatement(sql3);
                //     int k = ps3.executeUpdate();
                // p.println(sql2);
                // p.println(sql3);
                // ps.setFloat(4, 0);
                // p.println(ps.toString());
                int i = ps.executeUpdate();
                // p.println(i);
                s.executeQuery("commit");
                response.sendRedirect(
                        "http://localhost:8085/IT-Department/it?year=" + year);
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