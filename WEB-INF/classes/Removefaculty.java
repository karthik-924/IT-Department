
import java.io.*;
import java.sql.*;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Removefaculty extends HttpServlet {
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
                ResultSet r = s.executeQuery("select * from it" + request.getParameter("year")
                        + " where section='" + request.getParameter("section") + "'");

                int co1 = 0, co2 = 0;
                // p.println(c);
                while (r.next()) {
                    count = r.getString("count");
                    co = Integer.parseInt(count);
                    for (int i = 1; i <= co; i++) {
                        String faculty = "faculty" + i;
                        String fsubject = "subject" + i;
                        // if (r.getString(faculty).equals("deleted") && r.getString(subject).equals("deleted")) {
                        //     p.println(r.getString(faculty)+" "+r.getString(fsubject));
                        // } else {
                        if (r.getString(faculty).equals(name) && r.getString(fsubject).equals(subject))
                            co1 = co2 = i;
                        // }
                    }

                    // response.sendRedirect("/IT-Department/signin.html");
                }
                Statement sub = c.createStatement();
                ResultSet subco = s.executeQuery("select * from users");
                int subjectcount = 0;
                while (subco.next()) {
                    subjectcount = subco.getInt("subjectcount");
                }
                String column = "FACULTY" + co1;
                DatabaseMetaData md = c.getMetaData();

                ResultSet rs = md.getColumns(null, null, table, column);
                if (rs.next()) {
                    String sql = "update it" + year + " set faculty" + co1 + "='deleted' where section='" + section
                            + "'";
                    PreparedStatement ps = c.prepareStatement(sql);
                    int i = ps.executeUpdate();
                    p.println("done");
                    String sql2 = "update it" + year + " set subject" + co2 + "='deleted' where section='" + section
                            + "'";
                    PreparedStatement ps2 = c.prepareStatement(sql2);
                    int j = ps2.executeUpdate();
                    String colu = subject + "rating"+year+section;
                    colu = colu.replaceAll("\\s", "");
                    colu = colu.toLowerCase();
                    p.println(colu);
                    String sql3 = "alter table users drop column " + colu;
                    PreparedStatement ps3 = c.prepareStatement(sql3);
                    int k = ps3.executeUpdate();
                    // String sql3 = "update it" + year + " set count='" + (co - 1) + "' where section='" + section + "'";
                    // PreparedStatement ps3 = c.prepareStatement(sql3);
                    // int k = ps3.executeUpdate();
                    String sql4 = "update users set subjectcount=" + (subjectcount - 1);
                    PreparedStatement ps4 = c.prepareStatement(sql4);
                    int l = ps4.executeUpdate();
                }
                // else {
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

                // p.println(i);
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