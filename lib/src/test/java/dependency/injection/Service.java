package dependency.injection;

import dependency.injection.annotation.Component;

@Component("Service")
public class Service {
    public void say(String s) {
        System.out.println(s);
    }
}
