import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    var xValues = ArrayList<Double>()

    var x: Double = -7.0
    while (x <= 7.0) {
        xValues.add(x);

        x += 0.1
    }

    var epsilon = 0.1

    val sinValues = xValues.map { it -> listOf(it, sin(it, epsilon)) }
    val logValues = xValues.map { it -> listOf(it, ln(it, epsilon))}
    val eqSystemValues = xValues.map { it -> listOf(it, func(it, epsilon)) }

    val headerRow = listOf("x", "y")
    csvWriter().open("sin_func.csv") {
        writeRow(headerRow)
        writeRows(sinValues)
    }

    csvWriter().open("ln_func.csv") {
        writeRow(headerRow)
        writeRows(logValues)
    }

    csvWriter().open("eq_func.csv") {
        writeRow(headerRow)
        writeRows(eqSystemValues)
    }

}