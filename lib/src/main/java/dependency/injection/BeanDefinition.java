package dependency.injection;

import java.lang.reflect.InvocationTargetException;

public class BeanDefinition {
    private String id;
    private Class clazz;

    public BeanDefinition(String id, Class clazz) {
        this.setId(id);
        this.setClazz(clazz);
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getInstance() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return clazz.getDeclaredConstructor().newInstance();
    }

}
