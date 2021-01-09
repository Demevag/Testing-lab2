import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt

fun rightCos(x: Double) : Double
{
    val absCos = sqrt(1 - kotlin.math.sin(x) * kotlin.math.sin(x))
    return if (x <= PI/2) absCos else -absCos
}

@RunWith(Parameterized::class)
internal class TrigonometryTest(
        private val args: Triple<Double, Double, (Double, Double)->Double>)
{

    @org.junit.Test
    fun cosTest() {
        val (x, eps, sinFunc) = args
        assertEquals(kotlin.math.cos(x), cos(x, eps, sinFunc), eps);
        assertEquals(kotlin.math.cos(x), cos(x, eps), eps);
    }

    @org.junit.Test
    fun secTest() {
        val (x, eps, sinFunc) = args
        assertEquals(1/ kotlin.math.cos(x), sec(x, eps, sinFunc), eps)
        assertEquals(1/ kotlin.math.cos(x), sec(x, eps), eps)
    }

    @org.junit.Test
    fun cscTest() {
        val (x, eps, sinFunc) = args
        assertEquals(1/kotlin.math.sin(x), csc(x, eps, sinFunc), eps)
        assertEquals(1/kotlin.math.sin(x), csc(x, eps), eps)
    }

    @org.junit.Test
    fun tanTest() {
        val (x, eps, sinFunc) = args
        assertEquals(kotlin.math.tan(x), tan(x, eps, sinFunc), eps)
        assertEquals(kotlin.math.tan(x), tan(x, eps), eps)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Triple<Double, Double, (Double, Double)->Double>> {
            val sinFuncMock = {x: Double, eps: Double ->
                when (x) {
                    PI/6 -> 0.5
                    PI/4 -> sqrt(2.0)/2
                    PI/3 -> sqrt(3.0)/2
                    5*PI/4 -> -sqrt(2.0)/2
                    Double.NEGATIVE_INFINITY -> Double.NaN
                    Double.POSITIVE_INFINITY -> Double.NaN
                    Double.NaN -> Double.NaN
                    else -> {
                        kotlin.math.sin(x)
                    }
                }
            }

            return arrayListOf(
                    Triple(Double.NaN, 1e-4, sinFuncMock),
                    Triple(Double.POSITIVE_INFINITY, 1e-4, sinFuncMock),
                    Triple(Double.NEGATIVE_INFINITY, 1e-4, sinFuncMock),
                    Triple(PI/6, 1e-4, sinFuncMock),
                    Triple(PI/4, 1e-4, sinFuncMock),
                    Triple(PI/3, 1e-4, sinFuncMock),
                    Triple(5*PI/4, 1e-4, sinFuncMock),
            ).toList()
        }
    }
}