import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import javax.servlet.*;

public class SimpleServletExample extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter pw = res.getWriter();
        pw.print("Helló világ!" + req.getParameter("name"));
    }

}