import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ui2016 on 6/29/2016.
 */
public class TokenFilter implements Filter {
    @Override
    public void init (FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        PrintWriter writer = res.getWriter();

        if (req.getMethod().equals("GET")) {
            //check if the tokens match
            String header = req.getHeader("Authorization");
            if (header == null) {
                writer.println("No token found in the header");
                res.sendError(401);
                return;
            } else {
                //read the token from the request header
                System.out.println("Header: " + header);
                String extractedToken = header.substring(header.indexOf(":") + 1);
                System.out.println("Extracted token: " + extractedToken);
                if (!TokenStorage.getInstance().getToken(req.getSession().getId()).equals(extractedToken)) {
                    res.sendError(401); //unauthorized
                    return;
                }
            }
        } else if (req.getMethod().equals("POST")) {
            String createdToken = TokenStorage.getInstance().putToken(req.getSession().getId());
            TokenStorage.getInstance().putToken(createdToken);
            writer.println(createdToken);
        } else if (req.getMethod().equals("DELETE")) {
            TokenStorage.getInstance().removeToken(req.getSession().getId());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy () {

    }
}
