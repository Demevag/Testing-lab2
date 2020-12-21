import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.PI
import kotlin.math.sin

@RunWith(Parameterized::class)
internal class LNTest(
        private val x: Double,
        private val eps: Double)
{

    @org.junit.Test
    fun lnTest() {
        assertEquals(kotlin.math.ln(x), ln(x, eps), eps);
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Double>> {
            return arrayListOf(
                    arrayOf(0.0, 1e-5),
                    arrayOf(0.008, 1e-5),
                    arrayOf(0.5, 1e-5),
                    arrayOf(1.0, 1e-5),
                    arrayOf(10.0, 1e-5),
            ).toList()
        }
    }
}