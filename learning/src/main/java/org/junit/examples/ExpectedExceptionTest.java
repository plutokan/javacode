package org.junit.examples;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.Description;

/**
 * <code>ExpectedExceptionTest</code>
 *
 *ExpectedException：异常测试，在上一篇文章中介绍的@Test(expected=xxx)用法本质就是利用了这个Rule。
 *相比之前的用法ExpectedException提供了灵活匹配规则，可以根据message、cause和异常的具体类型匹配。
 *
 * @since learning Jun 7, 2016
 *
 */
public class ExpectedExceptionTest {

    @Rule
    public ExpectedException exp = ExpectedException.none();
    
    @Test
    public void expectException()
    {
        exp.expect(IndexOutOfBoundsException.class);
        throw new IndexOutOfBoundsException("Exception method.");
    }

    @Test
    public void expectMessage()
    {
        exp.expectMessage("Hello Worlda");
        throw new RuntimeException("Hello World will throw exception.");
    }

    @Test
    public void expectCourse()
    {
//        exp.expectCause(new BaseMatcher<IllegalArgumentException>()
//        {
//
//            public boolean matches(Object item)
//            {
//                return item instanceof IllegalArgumentException;
//            }
//
//            public void describeTo(Description description)
//            {
//                description.appendText("Expected Cause Error.");
//            }
//
//        });
        
        Throwable cause = new IllegalArgumentException("Cause Test.");
        throw new RuntimeException(cause);
    }

}
