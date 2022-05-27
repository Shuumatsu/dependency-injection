package dependency.injection;

import dependency.injection.annotation.Autowire;
import dependency.injection.annotation.Controller;

@Controller
public class Consumer {
    @Autowire
    private Service service;

    public void say(String s) {
        service.say(s);
    }
}
