import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class IT extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                response.setContentType("text/html");
                PrintWriter p = response.getWriter();
                // String mail = request.getParameter("email");
                String password = null;
                int valid = 0;
                Cookie ck[] = request.getCookies();
                if (ck != null) {
                        try {
                                valid = 0;
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection c = DriverManager.getConnection(
                                                "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
                                Statement s = c.createStatement();
                                ResultSet r = s.executeQuery("select * from it" + request.getParameter("year"));
                                p.println("<html>");
                                p.println("<head>");
                                p.println(
                                                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\"/>");
                                p.println(
                                                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>");
                                p.println(
                                                "<link rel=\"stylesheet\" media=\"screen\" href=\"https://fontlibrary.org//face/youngserif\" type=\"text/css\"/></script>");
                                p.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>");

                                p.println("<title>Information Technology!</title>");
                                p.println("<style>");
                                p.println(".main {margin-left: 2%;}");
                                p.println(".active{color:white}");
                                p.println(".card {width: 200%; margin-top: 20%;}");
                                p.println(
                                                ".grid {align-items: center; justify-content: center; display: grid; grid-template-columns: 300px 300px; grid-row: auto auto; grid-column-gap: 50px; grid-row-gap: 40px; margin-right: 5%;}");
                                p.println(".rowarrange {display: flex; flex-direction: row;}");
                                p.println("span{margin:0 auto}");
                                // p.println(".nav-item{height: 10%}");
                                p.println(".buttoncenter{display: flex;justify-content: center;margin-top:10px;margin-left:5%;}");
                                p.println(".logout{position:absolute;right:0;}");
                                p.println(".adjust{width: 20%;margin: 0 auto;margin-top:5px;padding-left:5%;}");
                                p.println(".navone{margin-left:10%;margin-right: 10%;margin-top: 10%; }");
                                p.println("@media screen and (max-width: 900px) {.grid {grid-template-columns: 200px 200px;}}");
                                p.println(
                                                "@media screen and (max-width: 576px) {.grid {grid-template-columns: 300px; grid-row: auto;}.card {width: 100%;}}");
                                p.println("@media screen and (max-width: 992px) {.navone{margin-left:20%}}");
                                p.println("</style>");
                                p.println("</head>");
                                p.println("<body>");
                                p.println("<center> <h1 id=\"title\" style=\"font-family: 'YoungSerifRegular'\">INFORMATION TECHNOLOGY</h1> </center>");
                                if ((request.getParameter("section") == null))
                                        p.print("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark rowarrange\"><div id=\"navbarNav\" class=\"main rowarrange\"><center><ul class=\"navbar-nav rowarrange\"><li class=\"nav-item rowarrange\" style=\"width: fit-content;\"><a class=\"nav-link\" href=\"/IT-Department/mainpage\">Home</a><p class=\"navone\" style=\"color: white;\"></p></li><li class=\"nav-item\" style=\"width:60px\"><a class=\"nav-link active\" href=\"/IT-Department/it?year="
                                                        + request.getParameter("year") + "\">IT-"
                                                        + request.getParameter("year") + "</a></li>");
                                if (!(request.getParameter("section") == null)) {
                                        p.print("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark rowarrange\"><div id=\"navbarNav\" class=\"main rowarrange\"><center><ul class=\"navbar-nav rowarrange\"><li class=\"nav-item rowarrange\" style=\"width: fit-content;\"><a class=\"nav-link\" href=\"/IT-Department/mainpage\">Home</a><p class=\"navone\" style=\"color: white;\"></p></li><li class=\"nav-item\" style=\"width:60px\"><a class=\"nav-link\" href=\"/IT-Department/it?year="
                                                        + request.getParameter("year") + "\">IT-"
                                                        + request.getParameter("year") + "</a></li>");
                                        p.println("<li class=\"nav-item rowarrange\" style=\"width:60px\"><p class=\"navone\" style=\"color: white; margin-left:0;\"></p><a class=\"nav-link active\" href=\"/IT-Department/it"
                                                        + request.getParameter("section") + "\">IT-"
                                                        + request.getParameter("section")
                                                        + "</a></li></ul></center></div></nav>");
                                } else {
                                        p.println("</ul></center></div><div class=\"logout btn btn-danger\"><a class=\"nav-link\" href=\"/IT-Department/logout\">Logout</a></div></nav>");
                                        p.println("<center>");
                                        p.println("<div class=\"grid\">");
                                        while (r.next()) {
                                                p.println("<div><div class=\"col-sm-6\"><div class=\"card\"><div class=\"card-body\"><h5 class=\"card-title\">IT</h5><p class=\"card-text\">"
                                                                + r.getString("section")
                                                                + " section</p><a href=\"/IT-Department/faculty?year="
                                                                + request.getParameter("year") + "&section="
                                                                + r.getString("section")
                                                                + "\" class=\"btn btn-primary\">Visit</a></div></div></div></div>");
                                        }
                                }
                                p.println("</div>");
                                if (ck[0].getValue().equals("admin101")) {
                                        p.println("<div id=\"ad\" class=\" buttoncenter\"><button type=\"button\" class=\"btn btn-success\">Add Section</button></div>");
                                        p.println("<div id=\"remove\" class=\" buttoncenter\"><button type=\"button\" class=\"btn btn-danger\">Remove Section</button></div>");

                                        // p.println("<div><div class=\"col-sm-6\"><div class=\"card\"><div
                                        // class=\"card-body\"><h5 class=\"card-title\">IT</h5><p
                                        // class=\"card-text\">2ND YEAR</p><a href=\"\" class=\"btn
                                        // btn-primary\">Visit</a></div></div></div></div>");
                                        // p.println("<div><div class=\"col-sm-6\"><div class=\"card\"><div
                                        // class=\"card-body\"><h5 class=\"card-title\">IT</h5><p
                                        // class=\"card-text\">3RD YEAR</p><a href=\"\" class=\"btn
                                        // btn-primary\">Visit</a></div></div></div></div>");
                                        // p.println("<div><div class=\"col-sm-6\"><div class=\"card\"><div
                                        // class=\"card-body\"><h5 class=\"card-title\">IT</h5><p
                                        // class=\"card-text\">4TH YEAR</p><a href=\"\" class=\"btn
                                        // btn-primary\">Visit</a></div></div></div></div>");

                                        p.println("<div class=\"forms\">");
                                        p.println("<form id=\"toggle\" style=\"display:none\" class=\"adjust\" action=\"/IT-Department/addsection\" method=\"post\">");
                                        p.println("<input type=\"hidden\" name=\"year\" value=\""
                                                        + request.getParameter("year")
                                                        + "\"\\>");
                                        p.println("<input type=\"hidden\" name=\"section\" value=\""
                                                        + request.getParameter("section")
                                                        + "\"\\>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputEmail1\" class=\"form-label\">Department</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\" name=\"name\" aria-describedby=\"emailHelp\">");
                                        p.println("</div>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputPassword1\" class=\"form-label\">Section</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputPassword1\" name=\"sectionf\">");
                                        p.println("</div>");
                                        p.println("<center><button type=\"submit\" class=\"btn btn-primary center\">Add</button><br><br>");
                                        p.println("</center></form>");
                                        p.println("<form id=\"toggler\" style=\"display:none\" class=\"adjust\" action=\"/IT-Department/removesection\" method=\"post\">");
                                        p.println("<input type=\"hidden\" name=\"year\" value=\""
                                                        + request.getParameter("year")
                                                        + "\"\\>");
                                        p.println("<input type=\"hidden\" name=\"section\" value=\""
                                                        + request.getParameter("section")
                                                        + "\"\\>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputEmail1\" class=\"form-label\">Department</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\" name=\"name\" aria-describedby=\"emailHelp\">");
                                        p.println("</div>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputPassword1\" class=\"form-label\">Section</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputPassword1\" name=\"sectionf\">");
                                        p.println("</div>");
                                        p.println("<center><button type=\"submit\" class=\"btn btn-primary center\">Remove</button><br><br>");
                                        p.println("</form>");
                                        p.println("</div>");
                                        // p.println(request.getParameter("section"));
                                        p.println("<script>");
                                        p.println("$(\"#remove\").click(function(){$(\"#toggle\").hide();$(\"#toggler\").toggle();});");
                                        p.println("$(\"#ad\").click(function(){$(\"#toggler\").hide();$(\"#toggle\").toggle();});");
                                        // p.println("function myFunctionr() {console.log('clicked1');");
                                        // p.println("var x = document.getElementById(\"toggler\");");
                                        // p.println("if (x.style.display === \"none\") {");
                                        // p.println("x.style.display = \"block\";");
                                        // p.println("} else {");
                                        // p.println("x.style.display = \"none\";");
                                        // p.println("}}");
                                        p.println("</script>");
                                }
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