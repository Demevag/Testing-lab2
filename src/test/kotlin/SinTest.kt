import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.PI
import kotlin.math.sin

@RunWith(Parameterized::class)
internal class SinTest(
        private val x: Double,
        private val eps: Double)
{

    @org.junit.Test
    fun sinTest() {
        assertEquals(sin(x), sin(x, eps), eps);
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Double>> {
            return arrayListOf(
                    arrayOf(-PI, 1e-5),
                    arrayOf(-PI/2, 1e-5),
                    arrayOf(-1.0, 1e-5),
                    arrayOf(0.0, 1e-5),
                    arrayOf(1.0, 1e-5),
                    arrayOf(PI/2, 1e-5),
                    arrayOf(PI, 1e-5),
                    arrayOf(PI/6, 1e-5),
            ).toList()
        }
    }
}