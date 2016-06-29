import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ui2016 on 6/29/2016.
 */
public class TokenStorage {

    private static TokenStorage instance = null;

    private ConcurrentHashMap<String, String> tokens =
            new ConcurrentHashMap<>();

    protected TokenStorage () {
    }

    public static TokenStorage getInstance () {
        if (instance == null) {
            synchronized (TokenStorage.class) {
                if (instance == null) {
                    instance = new TokenStorage();
                }
            }
        }
        return instance;
    }

    public String putToken (String sessionId) {
        String uuid = UUID.randomUUID().toString();
        tokens.put(sessionId, uuid);
        return uuid;
    }

    public void removeToken (String sessionId) {
            tokens.remove(sessionId);
    }

    public String getToken(String sessionId) {
        return tokens.get(sessionId);
    }


}
