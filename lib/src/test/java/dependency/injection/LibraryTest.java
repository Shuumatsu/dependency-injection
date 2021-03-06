/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dependency.injection;

import org.junit.jupiter.api.Test;

import dependency.injection.annotation.Controller;
import dependency.injection.impl.AnnotationApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    @Test
    void basic() throws ClassNotFoundException, RuntimeException {
        ApplicationContext context = new AnnotationApplicationContext(Config.class);
        var consumer = context.getBean(Consumer.class);
        consumer.say("basic test");
    }
}
