import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by ui2016 on 6/29/2016.
 */
public class UserProfileListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded (HttpSessionBindingEvent httpSessionBindingEvent) {
        if (httpSessionBindingEvent.getName().equals("userProfile")) {
            TokenStorage.getInstance().putToken(httpSessionBindingEvent.getSession().getId());
        }
    }

    @Override
    public void attributeRemoved (HttpSessionBindingEvent httpSessionBindingEvent) {
        if (httpSessionBindingEvent.getName().equals("userProfile")) {
            TokenStorage.getInstance().removeToken(httpSessionBindingEvent.getSession().getId());
        }

    }

    @Override
    public void attributeReplaced (HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
