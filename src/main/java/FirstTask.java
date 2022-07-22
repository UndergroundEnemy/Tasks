import org.testng.annotations.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class FirstTask implements java.lang.reflect.InvocationHandler {
    public static void main(String[] args) {

        //Test
        MethodInterception methodInterception = new MethodInterception();
        System.out.println(methodInterception.createPage(FirstTask.class));
    }

    private static InvocationHandler handler = new FirstTask();
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    @Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @Target({METHOD, TYPE})
    public @interface Selector {
        String xpath();
    }
    public interface MainPage {

        @Selector(xpath = ".//*[@test-attr='input_search']")
        String textInputSearch();

        @Selector(xpath = ".//*[@test-attr='button_search']")
        String buttonSearch();
    }

    public static class MethodInterception {

        @Test
        public void annotationValue() {
            MainPage mainPage = createPage(MainPage.class);
            assertNotNull(mainPage);
            assertEquals(mainPage.buttonSearch(), ".//*[@test-attr='button_search']");
            assertEquals(mainPage.textInputSearch(), ".//*[@test-attr='input_search']");
        }

        private MainPage createPage(Class clazz) {
            return (MainPage) java.lang.reflect.Proxy.newProxyInstance(
                    clazz.getClassLoader(),
                    new Class<?>[] {MainPage.class},
                    handler
            );
        }
    }
}
