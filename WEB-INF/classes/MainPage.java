import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class MainPage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter p = response.getWriter();
        // String name = request.getParameter("username");
        // String pass = request.getParameter("password");
        //String mail = request.getParameter("email");
        String password = null;
        int valid = 0;
        
        Cookie ck[] = request.getCookies();
        // p.println(name);
        // p.println((ck[0].getValue()).equals(name));
        if (ck!=null) {
            try {
                valid = 0;
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection c = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
                Statement s = c.createStatement();
                //ResultSet r = s.executeQuery("select * from users");
                // for(int i=0;i<ck.length;i++){  
                // p.println("<br>"+ck[i].getName()+" "+ck[i].getValue());//printing name and value of cookie  
                // }  
                p.println("<html>");
                p.println("<head>");
                p.println(
                        "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\"/>");
                p.println(
                        "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>");
                p.println(
                        "<link rel=\"stylesheet\" media=\"screen\" href=\"https://fontlibrary.org//face/youngserif\" type=\"text/css\"/></script>");

                p.println("<style>");
                p.println(".main {margin-left: 2%;}");
                p.println(".active{color:white}");
                p.println(".card {width: 200%; margin-top: 20%;}");
                p.println(".logout{position:absolute;right:0;}");
                p.println(
                        ".grid {align-items: center; justify-content: center; display: grid; grid-template-columns: 300px 300px; grid-row: auto auto; grid-column-gap: 50px; grid-row-gap: 40px; margin-right: 5%;}");
                p.println("@media screen and (max-width: 900px) {.grid {grid-template-columns: 200px 200px;}}");
                p.println(
                        "@media screen and (max-width: 576px) {.grid {grid-template-columns: 300px; grid-row: auto;}.card {width: 100%;}}");
                p.println("</style>");
                p.println("<title>Information Technology!</title>");
                p.println("</head>");
                p.println("<body>");
                p.println(
                        "<center> <h1 id=\"title\" style=\"font-family: 'YoungSerifRegular'\">INFORMATION TECHNOLOGY</h1> </center>");
                p.println(
                        "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\"><div id=\"navbarNav\" class=\"main\"><ul class=\"navbar-nav\"><li class=\"nav-item\"><a class=\"nav-link active\" href=\"/IT-Department/mainpage\">Home</a></li></ul></div><div class=\"logout btn btn-danger\"><a class=\"nav-link\" href=\"/IT-Department/logout\">Logout</a></div></nav>");
                p.println("<center>");
                p.println("<div class=\"grid\">");
                p.println(
                        "<div><div class=\"col-sm-6\"><div class=\"card\"><div class=\"card-body\"><h5 class=\"card-title\">IT</h5><p class=\"card-text\">1ST YEAR</p><a href=\"/IT-Department/it?year=1\" class=\"btn btn-primary\">Visit</a></div></div></div></div>");
                p.println(
                        "<div><div class=\"col-sm-6\"><div class=\"card\"><div class=\"card-body\"><h5 class=\"card-title\">IT</h5><p class=\"card-text\">2ND YEAR</p><a href=\"/IT-Department/it?year=2\" class=\"btn btn-primary\">Visit</a></div></div></div></div>");
                p.println(
                        "<div><div class=\"col-sm-6\"><div class=\"card\"><div class=\"card-body\"><h5 class=\"card-title\">IT</h5><p class=\"card-text\">3RD YEAR</p><a href=\"/IT-Department/it?year=3\" class=\"btn btn-primary\">Visit</a></div></div></div></div>");
                p.println(
                        "<div><div class=\"col-sm-6\"><div class=\"card\"><div class=\"card-body\"><h5 class=\"card-title\">IT</h5><p class=\"card-text\">4TH YEAR</p><a href=\"/IT-Department/it?year=4\" class=\"btn btn-primary\">Visit</a></div></div></div></div>");
                p.println("</div>");
                p.println("</center>");
                p.println("</body>");
                p.println("</html>");
                s.close();
                c.close();
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
        }
        else {
            response.setHeader("refresh", "1;url=http://localhost:8085/IT-Department/index.html");
    }
    }
}