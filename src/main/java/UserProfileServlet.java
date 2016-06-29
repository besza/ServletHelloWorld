import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ui2016 on 6/28/2016.
 */
public class UserProfileServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        HttpSession session = req.getSession();

        PrintWriter writer = resp.getWriter();

        if (session.getAttribute("user") != null) {
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(session.getAttribute("user"));
            writer.print(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain;charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        ObjectMapper mapper = new ObjectMapper();

        //System.out.println(mapper);
        UserProfile profile = mapper.readValue(req.getReader(), UserProfile.class);

        HttpSession session = req.getSession();

        session.setAttribute("user", profile);

        writer.print("Success!");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); //simple overwrite
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
