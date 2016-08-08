package org.junit.examples;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


/**
 * <code>TheoriesTest</code>
 * 
 * Theories：意为原理或者推测的意思，我觉得这里应该是取推测。Theories继承自BlockJUnit4ClassRunner，
 * 提供了除Parameterized之外的另一种参数测试解决方案——似乎更强大。
 * Theories不再需要使用带有参数的Constructor而是接受有参的测试方法，修饰的注解也从@Test变成了@Theory，
 * 而参数的提供则变成了使用@DataPoint或者@Datapoints来修饰的变量，两者的唯一不同是前者代表一个数据后者代表一组数据。
 * Theories会尝试所有类型匹配的参数作为测试方法的入参（有点排列组合的意思）。
 *
 * @since learning Jun 7, 2016
 *
 */
@RunWith(Theories.class)
public class TheoriesTest {

    @DataPoints
    public static String[] names = { "Tony", "Jim" };

    @DataPoints
    public static int[] ageValue1 = { 10, 20 };

    @Theory
    public void testMethod(String name, int age) {
        System.out.println(String.format("%s's age is %s", name, age));
    }

}
