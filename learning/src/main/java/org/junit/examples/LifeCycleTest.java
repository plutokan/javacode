package org.junit.examples;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <code>LifeCycleTest</code>
 *
 * <pre>
{@link BeforeClass @BeforeClass}：修饰static的方法，在整个类执行之前执行该方法一次。比如你的测试用例执行前需要一些高开销的资源（连接数据库）可以用@BeforeClass搞定。
值得注意的是如果测试用例类的父类中也存在@BeforeClass修饰的方法，它将在子类的@BeforeClass之前执行。
{@link AfterClass @AfterClass}：同样修饰static的方法，在整个类执行结束前执行一次。如果你用@BeforeClass创建了一些资源现在是时候释放它们了。
{@link Before @Before}：修饰public void的方法，在每个测试用例（方法）执行时都会执行。
{@link After @After}：修饰public void的方法，在每个测试用例执行结束后执行。
Constructor：每个测试用例都会重新创建当前的Class实例，可以看到Constructor执行了两次。
 * </pre>
 * 
 * @since learning Jun 7, 2016
 *
 */
public class LifeCycleTest {
    public LifeCycleTest() {
        super();
        System.out.println("<<Person Constructor>>");
    }

    @BeforeClass
    public static void beforeClassM() {
        System.out.println("<<Before Class>>");
    }

    @Before
    public void beforeM() {
        System.out.println("<<Before>>");
    }

    @AfterClass
    public static void afterClassM() {
        System.out.println("<<After Class>>");
    }

    @After
    public void after() {
        System.out.println("<<After>>");
    }

    @Test
    public void testMethod1() {
        System.out.println("Test Method 1.");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test Method 2.");
    }
}
