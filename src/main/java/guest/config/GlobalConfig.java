package guest.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by user on 08.10.2018.
 */
public class GlobalConfig {

    private static final String CONFIG_NAME = "guests.properties";
    private static final Properties GLOBAL_COFIG = new Properties();

    // начальная загрузка параметров из файла по умолчанию
    public static void initGlobalConfig() throws IOException {
        initGlobalConfig(null);
    }

    // загрузка параметров из конфигурационного файла, имя которого передано в виде параметра
    // Если имя null или пустое - берем файл по умолчанию.
    public static void initGlobalConfig(String name) throws IOException {
        if (name != null && !name.trim().isEmpty()) {
            GLOBAL_COFIG.load(new FileReader(name));
        } else {
            GLOBAL_COFIG.load(new FileReader(CONFIG_NAME));
        }
    }

    // Получить значение параметра из глобальной конфигурации по имени
    public static String getProperty(String property) {
        return GLOBAL_COFIG.getProperty(property);
    }
}
