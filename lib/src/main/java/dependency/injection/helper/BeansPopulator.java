package dependency.injection.helper;

import java.util.Map;

import dependency.injection.ApplicationContext;
import dependency.injection.annotation.Autowire;

public class BeansPopulator {

    private final ApplicationContext context;

    public BeansPopulator(ApplicationContext context) {
        this.context = context;
    }

    public void populate(Map<String, Object> instances) {
        for (var entry : instances.entrySet()) {
            var fs = entry.getValue().getClass().getDeclaredFields();
            for (var f : fs) {
                var autowire = f.getAnnotation(Autowire.class);
                if (autowire == null) {
                    continue;
                }

                var id = autowire.value();
                if (id.isEmpty()) {
                    id = f.getName();
                }
                f.setAccessible(true);
                try {
                    f.set(entry.getValue(), context.getBean(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
