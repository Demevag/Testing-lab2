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

fun rightTan(x: Double) : Double
{
    return kotlin.math.sin(x)/rightCos(x)
}

@RunWith(Parameterized::class)
internal class TrigonometryTest(
        private val args: Triple<Double, Double, (Double, Double)->Double>)
{

    @org.junit.Test
    fun cosTest() {
        val (x, eps, sinFunc) = args
        assertTrue(abs(kotlin.math.cos(x) - cos(x, eps, sinFunc)) < eps);
    }

    @org.junit.Test
    fun secTest() {
        val (x, eps, sinFunc) = args
        assertTrue(abs(1/ kotlin.math.cos(x) - sec(x, eps, sinFunc)) < eps);
    }

    @org.junit.Test
    fun cscTest() {
        val (x, eps, sinFunc) = args
        assertTrue(abs(1/kotlin.math.sin(x) - csc(x, eps, sinFunc)) < eps);
    }

    @org.junit.Test
    fun tanTest() {
        val (x, eps, sinFunc) = args
        assertTrue(abs(kotlin.math.tan(x) - tan(x, eps, sinFunc)) < eps);
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
                    else -> {
                        kotlin.math.sin(x)
                    }
                }
            }

            return arrayListOf(
                    Triple(PI/6, 1e-4, sinFuncMock),
                    Triple(PI/4, 1e-4, sinFuncMock),
                    Triple(PI/3, 1e-4, sinFuncMock),
                    Triple(PI, 1e-4, sinFuncMock),
                    Triple(5*PI/4, 1e-4, sinFuncMock),
            ).toList()
        }
    }
}