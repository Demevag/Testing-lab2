fun ln(x: Double, eps: Double): Double {
    if (x == Double.NEGATIVE_INFINITY ||
            x == Double.POSITIVE_INFINITY ||
            x == Double.NaN ||
            eps == Double.POSITIVE_INFINITY ||
            eps == Double.NEGATIVE_INFINITY ||
            eps == Double.NaN ||
            eps == 0.0) {
        return Double.NaN
    }

    if (x <= 0.0) {
        return Double.NEGATIVE_INFINITY;
    }
    var prev: Double
    var result = 0.0
    var i = 0
    do {
        prev = result
        result += Math.pow((x - 1) / (x + 1), 2 * i + 1.toDouble()) / (2 * i + 1)
        i++
    } while (Math.abs(prev - result) > eps/100)
    return 2 * result
}

fun log(x: Double, eps: Double, base: Double, lnFunc: (Double, Double) -> Double = ::ln): Double {
    val lnBase = lnFunc(base, eps)
    return if (lnBase == 0.0) Double.NaN else lnFunc(x, eps) / lnBase
}