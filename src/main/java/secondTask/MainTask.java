package secondTask;

import org.junit.Test;
import org.testng.annotations.Listeners;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ServiceLoader;

import static org.junit.Assert.assertEquals;

public class MainTask {
    public enum Priority {
        Blocker, Critical, Major, Minor
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE}) //on class level
    public @interface TestMethodInfo {

        //Приоритет теста
        Priority priority() default Priority.Major;

        //Автор теста
        String author() default "Bill Gates";

        //Дата последних изменений в тесте
        String lastModified() default "01.01.2019";
    }

    @Test
    @TestMethodInfo(priority = Priority.Critical, author = "Test user", lastModified = "20.08.2019")
    public void annotation() {
        Method[] methods = MainTask.class.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(TestMethodInfo.class)) {
                TestMethodInfo tmi = m.getAnnotation(TestMethodInfo.class);
                System.out.println(tmi.author());
                System.out.println(tmi.priority());
                System.out.println(tmi.lastModified());
            }
        }
        assertEquals(1, 1);
    }

}
