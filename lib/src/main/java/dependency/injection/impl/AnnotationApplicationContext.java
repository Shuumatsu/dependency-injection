package dependency.injection.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dependency.injection.ApplicationContext;
import dependency.injection.BeanDefinition;
import dependency.injection.BeanRegistry;
import dependency.injection.annotation.ComponentScan;
import dependency.injection.annotation.Configuration;
import dependency.injection.helper.BeanDefinitionsLoader;
import dependency.injection.helper.BeansPopulator;

public class AnnotationApplicationContext implements ApplicationContext, BeanRegistry {

    private Map<String, Object> instances = new ConcurrentHashMap<>();

    private List<BeanDefinition> definitions = new ArrayList<>();

    public AnnotationApplicationContext(Class<?> clazz) throws RuntimeException, ClassNotFoundException {
        var config = clazz.getAnnotation(Configuration.class);
        if (config == null) {
            throw new RuntimeException("Class " + clazz.getName() + " is not annotated with @Configuration");
        }

        var pkg = clazz.getAnnotation(ComponentScan.class);
        if (pkg == null) {
            return;
        }

        var loader = new BeanDefinitionsLoader(this);
        loader.load(pkg.value());

        var populator = new BeansPopulator(this);
        populator.populate(instances);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return null;
    }

    @Override
    public Object getBean(String id) {
        return instances.get(id);
    }

    @Override
    public <T> T getBean(String id, Class<T> clazz) {
        return (T) instances.get(id);
    }

    @Override
    public Map<String, Object> getBeans() {
        return instances;
    }

    @Override
    public void registerBeanDefinitions(List<BeanDefinition> ds) {
        definitions.addAll(ds);

    }

    @Override
    public void registerInstanceMapping(String id, Object instance) {
        instances.put(id, instance);
    }
}
