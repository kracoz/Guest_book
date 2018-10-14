package guest.dao;

/**
 * Created by user on 08.10.2018.
 */
public class ConnectionBuilderFactory {
    public static ConnectionBuilder getConnectionBuilder() {
        return new ComboConnectionBuilder();
    }
}
