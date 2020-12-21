import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class LogTest(
        private val args: Triple<Double, Double, (Double, Double)->Double>)
{
    @org.junit.Test
    fun log2Test() {
        val (x, eps, lnFunc) = args
        assertEquals(kotlin.math.log(x, 2.0), log(x, eps, 2.0, lnFunc), eps);
    }

    @org.junit.Test
    fun log3Test() {
        val (x, eps, lnFunc) = args
        assertEquals(kotlin.math.log(x, 3.0), log(x, eps, 3.0, lnFunc), eps);
    }

    @org.junit.Test
    fun log5Test() {
        val (x, eps, lnFunc) = args
        assertEquals(kotlin.math.log(x, 5.0), log(x, eps, 5.0, lnFunc), eps);
    }

    @org.junit.Test
    fun log10Test() {
        val (x, eps, lnFunc) = args
        assertEquals(kotlin.math.log(x, 10.0), log(x, eps, 10.0, lnFunc), eps);
    }
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Triple<Double, Double, (Double, Double)->Double>> {
            val lnFuncMock = {x: Double, eps: Double ->
                when (x) {
                    0.0 -> Double.NEGATIVE_INFINITY
                    -1.0 -> Double.NaN
                    -10.0 -> Double.NaN
                    else -> {
                        kotlin.math.ln(x)
                    }
                }
            }

            return arrayListOf(
                    Triple(0.0, 1e-5, lnFuncMock),
                    Triple(0.008, 1e-5, lnFuncMock),
                    Triple(0.5, 1e-5, lnFuncMock),
                    Triple(1.0, 1e-5, lnFuncMock),
                    Triple(10.0, 1e-5, lnFuncMock),
                    Triple(-10.0, 1e-5, lnFuncMock),
                    Triple(-1.0, 1e-5, lnFuncMock),
            ).toList()
        }
    }
}