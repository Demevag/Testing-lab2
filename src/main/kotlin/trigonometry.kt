

fun fact(x: Int): Double {
    var value = 1.0
    for (i in 2..x) value *= i.toDouble()
    return value
}

fun sin(x: Double, eps: Double): Double {
    if (x == Double.NEGATIVE_INFINITY ||
            x == Double.POSITIVE_INFINITY ||
            x == Double.NaN ||
            eps == Double.POSITIVE_INFINITY ||
            eps == Double.NEGATIVE_INFINITY ||
            eps == Double.NaN ||
            eps == 0.0) {
        return Double.NaN
    }
    var prev: Double
    var result = 0.0
    var i = 0
    do {
        prev = result
        result += Math.pow(-1.0, i.toDouble()) * Math.pow(x, (2 * i + 1).toDouble()) / fact(2 * i + 1)
        i++
    } while (Math.abs(prev - result) > eps)
    return result
}

fun cos(x: Double,
        eps: Double,
        sinFunc: (Double, Double) -> Double = ::sin): Double {
    return sinFunc(x + Math.PI / 2, eps)
}

fun sec(x: Double, eps: Double, sinFunc: (Double, Double) -> Double = ::sin): Double {
    val cos = cos(x, eps, sinFunc)
    return if (cos == 0.0) Double.POSITIVE_INFINITY else 1 / cos
}

fun csc(x: Double, eps: Double, sinFunc: (Double, Double) -> Double = ::sin): Double {
    val sin = sinFunc(x, eps)
    return if (sin == 0.0) Double.POSITIVE_INFINITY else 1 / sin
}

fun tan(x: Double, eps: Double, sinFunc: (Double, Double) -> Double = ::sin): Double {
    val cos = cos(x, eps, sinFunc)
    return if (cos == 0.0) Double.POSITIVE_INFINITY else sinFunc(x, eps) / cos
}