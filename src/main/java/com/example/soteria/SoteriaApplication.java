import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@ApplicationPath("/soteria")
public class SoteriaApplication extends Application {
    @Context
    private UriInfo uriInfo;

    public SoteriaApplication() {
        initializeServices();
    }

    private void initializeServices() {
        // Add service initialization code here
    }
}