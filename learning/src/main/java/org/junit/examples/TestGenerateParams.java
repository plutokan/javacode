package org.junit.examples;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * <code>TestGenerateParams</code>
 *
 * Parameterized：Parameterized继承自Suit，从这个身世和名字应该可以猜到一些因果了。
 * Parameterized是在参数上实现了Suit——修饰一个测试类，但是可以提供多组构造函数的参数用于测试不同场景。
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since learning Jun 7, 2016
 *
 */
@RunWith(Parameterized.class)
public class TestGenerateParams {

    private String greeting;
    private String mark;

    public TestGenerateParams(String greeting, String mark) {
        super();
        this.greeting = greeting;
        this.mark = mark;
    }

    @Test
    public void testParams() {
        System.out.println(greeting + mark);
    }

    /**
     * 这里的返回至少是二维数组
     * 
     * @return
     */
    @Parameters
    public static List<String[]> getParams() {
        return Arrays.asList(new String[][] { { "hello", "!" }, { "hi", "!" }, { "good morning", "." }, { "how are you", "?" } });
    }

}
