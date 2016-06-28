import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServletExample extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain; charset=utf-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter pw = res.getWriter();

        HttpSession session = req.getSession();

        if (session.getAttribute("tries") == null) {
            session.setAttribute("tries", Integer.valueOf(getInitParameter("number")));
        }

        if (req.getParameter("name") != null) {
            if ((Integer) session.getAttribute("tries") > 0) {
                session.setAttribute("tries", (Integer) session.getAttribute("tries") - 1);
                session.setAttribute("name", req.getParameter("name"));
                pw.print("Helló " + req.getParameter("name") + "!");
            } else {
                res.sendError(400); // bad request
            }

        } else {
            final Object nameParam = session.getAttribute("name");
            pw.print("Helló " + nameParam + "!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
    }
}