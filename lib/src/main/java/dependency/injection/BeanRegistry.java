package dependency.injection;

import java.util.List;

public interface BeanRegistry {

    void registerBeanDefinitions(List<BeanDefinition> ds);

    void registerInstanceMapping(String id, Object instance);

}
