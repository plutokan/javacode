package org.junit.examples;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <code>TestSuitMain</code>
 *
 *Suit就是个Runner！用来执行分布在多个类中的测试用例，
 *比如我存在SimpleFunctionTest和ComplexFunctionTest类分别测试Person的简单和复杂行为，
 *在茫茫的测试用例中如何一次执行所有与Person有关的测试呢——使用Suit。
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since learning Jun 7, 2016
 *
 */
@RunWith(Suite.class)
@SuiteClasses({LifeCycleTest.class, TestGenerateParams.class})
public class TestSuitMain {

}
