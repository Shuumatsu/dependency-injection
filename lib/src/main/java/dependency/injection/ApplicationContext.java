package dependency.injection;

import java.util.Map;

public interface ApplicationContext {
    <T> T getBean(Class<T> clazz);

    Object getBean(String id);

    <T> T getBean(String id, Class<T> clazz);

    Map<String, Object> getBeans();
}
