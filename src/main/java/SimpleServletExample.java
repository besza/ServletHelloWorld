import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import javax.servlet.*;

public class SimpleServletExample extends HttpServlet {

    private int triesLeft;

    @Override
    public void init() throws ServletException {
        triesLeft = Integer.valueOf(getInitParameter("number"));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.setCharacterEncoding("UTF-8");
        ServletContext context = this.getServletContext();
        PrintWriter pw = res.getWriter();
        if (req.getParameter("name") != null) {
            if (triesLeft > 0) {
                --triesLeft;
                context.setAttribute("name", req.getParameter("name"));
                pw.print("Helló " + req.getParameter("name") + "!");
            } else {
                res.sendError(400); // bad request
            }

        } else {
            final Object nameParam = context.getAttribute("name");
            pw.print("Helló " + nameParam + "!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter pw = res.getWriter();
        pw.print("Helló világ!" + req.getParameter("name"));
    }
}