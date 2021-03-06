package net.bytebuddy.pool;

import net.bytebuddy.description.method.AbstractMethodListTest;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import org.junit.After;
import org.junit.Before;

import java.lang.reflect.Method;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.anyOf;

public class TypePoolDefaultLazyMethodListTest extends AbstractMethodListTest<Method, MethodDescription.InDefinedShape> {

    private TypePool typePool;

    @Before
    public void setUp() throws Exception {
        typePool = TypePool.Default.ofSystemLoader();
    }

    @After
    public void tearDown() throws Exception {
        typePool.clear();
    }

    protected Method getFirst() throws Exception {
        return Foo.class.getDeclaredMethod("foo");
    }

    protected Method getSecond() throws Exception {
        return Foo.class.getDeclaredMethod("bar");
    }

    protected MethodList<MethodDescription.InDefinedShape> asList(List<Method> elements) {
        return typePool.describe(Foo.class.getName()).resolve().getDeclaredMethods().filter(anyOf(elements.toArray(new Method[elements.size()])));
    }

    protected MethodDescription.InDefinedShape asElement(Method element) {
        return new MethodDescription.ForLoadedMethod(element);
    }
}
