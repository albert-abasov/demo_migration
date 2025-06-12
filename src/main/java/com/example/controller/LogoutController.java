import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@Path("/logout")
public class LogoutController {
    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @GET
    public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        try {
            if (request.getUserPrincipal() != null) {
                request.logout();
                response.sendRedirect("user");
            } else {
                response.sendRedirect("error?message=User not logged in");
            }
        } catch (Exception e) {
            logger.error("Logout failed", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.sendRedirect("error?message=Logout failed");
            } catch (Exception redirectException) {
                logger.error("Redirect failed", redirectException);
            }
        }
    }
}