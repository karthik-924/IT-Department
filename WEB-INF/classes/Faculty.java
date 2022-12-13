import java.io.*;
import java.sql.*;
import java.lang.Math;
import java.lang.Thread.State;

import javax.swing.event.InternalFrameAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Faculty extends HttpServlet {
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                response.setContentType("text/html");
                PrintWriter p = response.getWriter();
                // String mail = request.getParameter("email");
                String password = null;
                int valid = 0;
                Cookie ck[] = request.getCookies();
                // p.println(name);
                // p.println((ck[0].getValue()).equals(name));
                if (ck != null) {
                        try {
                                valid = 0;
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                Connection c = DriverManager.getConnection(
                                                "jdbc:oracle:thin:@localhost:1521:xe", "system", "karthik");
                                Statement s = c.createStatement();
                                ResultSet r = s.executeQuery("select * from it" + request.getParameter("year")
                                                + " where section='" + request.getParameter("section") + "'");
                                p.println("<html>");
                                p.println("<head>");
                                p.println(
                                                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65\" crossorigin=\"anonymous\"/>");
                                p.println(
                                                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>");
                                p.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>");
                                p.println(
                                                "<link rel=\"stylesheet\" media=\"screen\" href=\"https://fontlibrary.org//face/youngserif\" type=\"text/css\"/></script>");
                                p.println("<link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\" />");
                                p.println("<title>Information Technology!</title>");
                                p.println("<style>");
                                p.println(".main {margin-left: 2%;}");
                                p.println(".active{color:white}");
                                p.println(".card {width: 200%; margin-top: 20%;}");
                                p.println(".rounded{width:110%;border: 1px solid gray;border-radius: 0.375rem;padding-top:24px;}");
                                p.println(
                                                ".grid {align-items: center; justify-content: center; display: grid; grid-template-columns: 300px 300px; grid-row: auto auto; grid-column-gap: 50px; grid-row-gap: 40px; margin-right: 5%;}");
                                p.println(".rowarrange {display: flex; flex-direction: row;}");
                                p.println("span{margin:0 auto}");
                                p.println(".centering{width: 30%;height: fit-content;position: absolute; /*Can also be `fixed`*/left: 0;right: 0;top: 0;bottom: 0;margin: auto;padding-bottom: 2%;background-color:white;border-radius:5%;}");
                                p.println(".adjust{width: 20%;margin: 0 auto;margin-top:5px;}");
                                p.println(".social-link {width: 30px;height: 30px;border: 1px solid #ddd;display: flex;align-items: center;justify-content: center;color: #666;border-radius: 50%;transition: all 0.3s;font-size: 0.9rem;}");
                                p.println(".social-link:hover, .social-link:focus {background: #ddd;text-decoration: none;color: #555;}");
                                p.println(".logout{position:absolute;right:0;}");
                                p.println(".shadow{box-shadow:5px 5px 5px 5px;margin-right:8%;background-color:white;padding:0;margin-top:2%;}");
                                // p.println(".nav-item{height: 10%}");
                                p.println("@media screen and (max-width: 1200px){.centering{width: 35%;}}");
                                p.println("@media screen and (max-width: 950px){.centering{width: 40%;}}");
                                p.println("@media screen and (max-width: 800px){.centering{width: 45%;}}");
                                p.println("@media screen and (max-width: 700px){.centering{width: 50%;}}");
                                p.println("@media screen and (max-width: 600px){.centering{width: 55%;}}");
                                p.println("@media screen and (max-width: 500px){.centering{width: 60%;}}");
                                p.println("@media screen and (max-width: 400px){.centering{width: 70%;}}");
                                p.println("@media screen and (max-width: 350px){.centering{width: 75%;}}");
                                p.println(".rating {display: inline-block;position: relative;height: 25px;line-height: 25px;font-size: 25px;}");
                                p.println(".rating label {position: absolute;top: 0;left: 0;height: 100%;cursor: pointer}");
                                p.println(".rating label:last-child {position: static;}");
                                p.println(".rating label:nth-child(1) {z-index: 5;}");
                                p.println(".rating label:nth-child(2) {z-index: 4;}");
                                p.println(".rating label:nth-child(3) {z-index: 3;}");
                                p.println(".rating label:nth-child(4) {z-index: 2;}");
                                p.println(".rating label:nth-child(5) {z-index: 1;}");
                                p.println(".rating label input {position: absolute;top: 0;left: 0;opacity: 0;}");
                                p.println(".rating label .icon {float: left;color: transparent;}");
                                p.println(".rating label:last-child .icon {color: black;}");
                                p.println(".rating:not(:hover) label input:checked ~ .icon,.rating:hover label:hover input ~ .icon {color: gold;}");
                                p.println(".rating label input:focus:not(:checked) ~ .icon:last-child {color: gold;text-shadow: 0 0 5px #09f;}");
                                p.println(".rowarrange{display;flex}");
                                p.println(".navone{margin-left:10%;margin-right: 10%;margin-top: 10%; }");
                                p.println(".buttoncenter{display: flex;justify-content: center;margin-top:10px;}}");
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
                                        p.println("<li class=\"nav-item rowarrange\" style=\"width:60px\"><p class=\"navone\" style=\"color: white; margin-left:0;\"></p><a class=\"nav-link active\" href=\"/IT-Department/faculty?year="
                                                        + request.getParameter("year") + "&section="
                                                        + request.getParameter("section") + "\">IT-"
                                                        + request.getParameter("section")
                                                        + "</a></li></ul></center></div><div class=\"logout btn btn-danger\"><a class=\"nav-link\" href=\"/IT-Department/logout\">Logout</a></div></nav>");
                                }
                                // p.println("<div><div class=\"col-sm-6\"><div class=\"card\"><div
                                // class=\"card-body\"><h5 class=\"card-title\">IT</h5><p
                                // class=\"card-text\">2ND YEAR</p><a href=\"\" class=\"btn
                                // btn-primary\">Visit</a></div></div></div></div>");
                                // p.println("<div><div class=\"col-sm-6\"><div class=\"card\"><div
                                // class=\"card-body\"><h5 class=\"card-title\">IT</h5><p
                                // class=\"card-text\">3RD YEAR</p><a href=\"\" class=\"btn
                                // btn-primary\">Visit</a></div></div></div></div>");
                                // p.println("<div><div class=\"col-sm-6\"><div class=\"car d\"><div
                                // class=\"card-body\"><h5 class=\"card-title\">IT</h5><p
                                // class=\"card-text\">4TH YEAR</p><a href=\"\" class=\"btn
                                // btn-primary\">Visit</a></div></div></div></div>");
                                p.println("</div>");
                                int num = 1;
                                p.println("<div class=\"container\">");
                                p.println("<div class=\"row text-center\">");
                                while (r.next()) {
                                        // p.println("working");
                                        if (!r.getString("count").equals("0")) {
                                                int count = Integer.parseInt(r.getString("count"));
                                                for (int i = 1; i <= count; i++) {
                                                        String faculty = "faculty" + i;
                                                        String subject = "subject" + i;
                                                        // p.println(faculty+" "+ subject);
                                                        // p.println((r.getString(faculty)==null) +"
                                                        // "+(r.getString(subject)==null));
                                                        if (r.getString(faculty).equals("deleted")
                                                                        && r.getString(subject).equals("deleted")) {
                                                                // p.println(faculty+" "+subject);
                                                        } else {
                                                                Statement w = c.createStatement();
                                                                Statement n = c.createStatement();
                                                                String column = r.getString(subject) + "rating"
                                                                                + request.getParameter("year")
                                                                                + request.getParameter("section");
                                                                column = column.replaceAll("\\s", "");
                                                                column = column.toLowerCase();
                                                                Float rating = 0f;
                                                                int countcolumn = 0;
                                                                ResultSet m = w.executeQuery("select avg(" + column
                                                                                + ") from users");
                                                                ResultSet colcount = n.executeQuery("select count(" + column
                                                                                + ") from users");
                                                                while (m.next()) {
                                                                        rating = m.getFloat(1);
                                                                }
                                                                while (colcount.next()) {
                                                                        countcolumn = colcount.getInt(1);
                                                                }
                                                                p.println("<div class=\"col-xl-3 col-sm-5 mb-4 shadow\">");
                                                                p.println("<div class=\"bg-white rounded shadow-sm py-5 px-4\"><img src=\"https://cdn-icons-png.flaticon.com/512/8907/8907269.png\" alt=\"\" width=\"100\" class=\"img-fluid rounded-circle mb-3 img-thumbnail shadow-sm\">");
                                                                p.println("<h5 class=\"mb-0\">" + r.getString(faculty)
                                                                                + "</h5><h6 class=\"mb-0\">"
                                                                                + r.getString(subject) + "</h6>");
                                                                p.println("<form class=\"rating\" id=\"rating\" action=\"/IT-Department/rating\" method=\"post\">");
                                                                p.println("<input type=\"hidden\" name=\"year\" value=\""
                                                                                + request.getParameter("year")
                                                                                + "\"\\>");
                                                                p.println("<input type=\"hidden\" name=\"section\" value=\""
                                                                                + request.getParameter("section")
                                                                                + "\"\\>");
                                                                p.println("<input type=\"hidden\" name=\"subject\" value=\""
                                                                                + r.getString(subject)
                                                                                + "\"\\>");
                                                                                p.println("<input type=\"hidden\" name=\"present\" value=\""
                                                                                + i
                                                                                + "\"\\>");
                                                                p.println("<div class=\"rowarrange\"><div class=\"rowarrange\"><label><input type=\"radio\" name=\"stars"
                                                                                + i
                                                                                + "\" value=\"1\" /><span class=\"icon\">★</span></label>");
                                                                p.println("<label><input type=\"radio\" name=\"stars"
                                                                                + i
                                                                                + "\" value=\"2\" /><span class=\"icon\">★</span><span class=\"icon\">★</span></label>");
                                                                p.println("<label><input type=\"radio\" name=\"stars"
                                                                                + i
                                                                                + "\" value=\"3\" /><span class=\"icon\">★</span><span class=\"icon\">★</span><span class=\"icon\">★</span></label>");
                                                                p.println("<label><input type=\"radio\" name=\"stars"
                                                                                + i
                                                                                + "\" value=\"4\" /><span class=\"icon\">★</span><span class=\"icon\">★</span><span class=\"icon\">★</span><span class=\"icon\">★</span></label>");
                                                                p.println("<label><input type=\"radio\" name=\"stars"
                                                                                + i
                                                                                + "\" value=\"5\" /><span class=\"icon\">★</span><span class=\"icon\">★</span><span class=\"icon\">★</span><span class=\"icon\">★</span><span class=\"icon\">★</span></label></div><div><p style=\"font-size:small;margin-bottom:0;\" id=\"rate"
                                                                                + i + "\"></p></div></div>");
                                                                p.println("<span style=\"font-size:small;\">Avg rating:"
                                                                                + rating
                                                                                + " ("+countcolumn+")</span><br/><input class=\"btn btn-success\" type=\"submit\" value=\"Rate\"/></form>");
                                                                p.println("</div>");
                                                                p.println("</div>");
                                                                p.println("<script>$('input[name=\"stars" + i
                                                                                + "\"]').change(function() {console.log('New star rating: ' + this.value);document.getElementById(\"rate"
                                                                                + i
                                                                                + "\").innerText=this.value});</script>");
                                                        }
                                                }
                                        }
                                }
                                p.println("</div></div>");
                                if (ck[0].getValue().equals("admin101")) {
                                        p.println("<div id=\"ad\" class=\" buttoncenter\"><button type=\"button\" class=\"btn btn-success\">Add Faculty</button></div>");
                                        p.println("<div id=\"remove\" class=\" buttoncenter\"><button type=\"button\" class=\"btn btn-danger\">Remove Faculty</button></div>");
                                        p.println("<div>");
                                        p.println("<form id=\"toggle\" style=\"display:none\" class=\"adjust\" action=\"/IT-Department/addfaculty\" method=\"post\">");
                                        p.println("<input type=\"hidden\" name=\"year\" value=\""
                                                        + request.getParameter("year")
                                                        + "\"\\>");
                                        p.println("<input type=\"hidden\" name=\"section\" value=\""
                                                        + request.getParameter("section")
                                                        + "\"\\>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputEmail1\" class=\"form-label\">Name</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\" name=\"name\" aria-describedby=\"emailHelp\">");
                                        p.println("</div>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputPassword1\" class=\"form-label\">Subject</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputPassword1\" name=\"subject\">");
                                        p.println("</div>");
                                        p.println("<center><button type=\"submit\" class=\"btn btn-primary center\">Add</button><br><br>");
                                        p.println("</center></form>");
                                        p.println("<form id=\"toggler\" style=\"display:none\" class=\"adjust\" action=\"/IT-Department/removefaculty\" method=\"post\">");
                                        p.println("<input type=\"hidden\" name=\"year\" value=\""
                                                        + request.getParameter("year")
                                                        + "\"\\>");
                                        p.println("<input type=\"hidden\" name=\"section\" value=\""
                                                        + request.getParameter("section")
                                                        + "\"\\>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputEmail1\" class=\"form-label\">Name</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputEmail1\" name=\"name\" aria-describedby=\"emailHelp\">");
                                        p.println("</div>");
                                        p.println("<div class=\"mb-3\">");
                                        p.println("<label for=\"exampleInputPassword1\" class=\"form-label\">Subject</label>");
                                        p.println("<input type=\"text\" class=\"form-control\" id=\"exampleInputPassword1\" name=\"subject\">");
                                        p.println("</div>");
                                        p.println("<center><button type=\"submit\" class=\"btn btn-primary center\">Remove</button><br><br>");
                                        p.println("</form>");
                                        p.println("</div>");
                                        // p.println(request.getParameter("section"));
                                        p.println("</center>");
                                }
                                p.println("<script>");

                                // function myFunction() {console.log('clicked');");
                                // p.println("var x = document.getElementById(\"toggle\");");
                                // p.println("if (x.style.display === \"none\") {");
                                // p.println("x.style.display = \"block\";");
                                // p.println("} else {");
                                // p.println("x.style.display = \"none\";");
                                // p.println("}}");
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
                                p.println("</body>");
                                p.println("</html>");
                                s.close();
                                c.close();
                        } catch (Exception e) {
                                System.out.println("Exception" + e);
                        }
                } else {
                        response.setHeader("refresh", "1;url=http://localhost:8085/IT-Department/index.html");
                }
        }
}