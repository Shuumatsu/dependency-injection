package dependency.injection.helper;

import java.io.File;
import java.util.Vector;

import dependency.injection.BeanDefinition;
import dependency.injection.BeanRegistry;
import dependency.injection.annotation.Component;

public class BeanDefinitionsLoader {

    private BeanRegistry registry;

    public BeanDefinitionsLoader(BeanRegistry register) {
        this.registry = register;
    }

    public void load(String pkg) throws ClassNotFoundException {
        register(pkg);
    }

    private void register(String pkg) throws ClassNotFoundException {
        var url = getClass().getClassLoader().getResource("/" + pkg.replace("\\.", "/"));
        var dir = new File(url.getFile());

        for (var file : dir.listFiles()) {
            if (file.isDirectory()) {
                register(pkg + "." + file.getName());
                continue;
            }

            var className = pkg + "." + file.getName().replace(".class", "").trim();
            var clazz = Class.forName(className);
            var anno = clazz.getAnnotation(Component.class);
            if (anno == null) {
                continue;
            }

            var ids = new Vector<String>(0);
            if (anno.value() != null && !anno.value().isEmpty()) {
                ids.add(anno.value());
            } else {
                var interfaces = clazz.getInterfaces();
                for (var i : interfaces) {
                    ids.add(i.getName());
                }
            }

            var ds = new Vector<BeanDefinition>(0);
            for (var id : ids) {
                ds.add(new BeanDefinition(id, clazz));
            }
            this.registry.registerBeanDefinitions(ds);
        }
    }

}
