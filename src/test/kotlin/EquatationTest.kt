import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.PI
import kotlin.math.sqrt

internal class EquatationTest
{
    val eps: Double = 0.0001;
    val sinFuncMock = { x: Double, eps: Double ->
        when (x) {
            PI /6 -> 0.5
            PI /4 -> sqrt(2.0) /2
            PI /3 -> sqrt(3.0) /2
            5* PI /4 -> -sqrt(2.0) /2
            else -> {
                kotlin.math.sin(x)
            }
        }
    }
    val lnFuncMock = { x: Double, eps: Double ->
        when (x) {
            0.0 -> Double.NEGATIVE_INFINITY
            -1.0 -> Double.NaN
            -10.0 -> Double.NaN
            else -> {
                kotlin.math.ln(x)
            }
        }
    }

    @org.junit.Test
    fun func1Tests() {
        assertEquals(-3.974873, func1(-PI/4,eps, sinFuncMock), eps)
        assertEquals(28.899990, func1(-PI/3,eps, sinFuncMock), eps)

        assertEquals(-3.974873, func1(-PI/4,eps), eps)
        assertEquals(28.899990, func1(-PI/3,eps), eps)
    }

    @org.junit.Test
    fun func2Test() {
        assertEquals(-3.239709, func2(0.1,eps, lnFuncMock), eps)
        assertEquals(1.957416, func2(7.0,eps, lnFuncMock), eps)

        assertEquals(-3.239709, func2(0.1,eps), eps)
        assertEquals(1.957416, func2(7.0,eps), eps)
    }

    @org.junit.Test
    fun funcTest() {
        assertEquals(-3.239709, func(0.1,eps, sinFuncMock, lnFuncMock), eps)
        assertEquals(0.1839269, func(-0.4,eps, sinFuncMock, lnFuncMock), eps)

        assertEquals(-3.239709, func(0.1,eps, sinFuncMock), eps)
        assertEquals(-3.239709, func(0.1,eps, ::sin, lnFuncMock), eps)
        assertEquals(-3.239709, func(0.1,eps), eps)

        assertEquals(0.1839269, func(-0.4,eps, sinFuncMock), eps)
        assertEquals(0.1839269, func(-0.4,eps, ::sin, lnFuncMock), eps)
        assertEquals(0.1839269, func(-0.4,eps), eps)
    }
}