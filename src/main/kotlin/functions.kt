
fun func1(x: Double, eps: Double, sinFunc: (Double, Double) -> Double = ::sin): Double {
    var firstBrackets = Math.pow((sec(x, eps, sinFunc) * sinFunc(x,eps)), 2.0);
    firstBrackets = firstBrackets / sinFunc(x, eps) + cos(x, eps, sinFunc)

    val first = Math.pow(sinFunc(x, eps), 2.0)
    val second = tan(x, eps, sinFunc) / (tan(x, eps, sinFunc) - csc(x, eps, sinFunc))
    val third = (csc(x, eps, sinFunc) + sinFunc(x, eps)) * tan(x, eps, sinFunc)

    val secondBrackets = first - second*third

    return firstBrackets*secondBrackets
}

fun func2(x: Double, eps: Double, lnFunc: (Double, Double) -> Double = ::ln): Double
{
    val innerBracket = Math.pow(log(x, eps, 5.0, lnFunc), 2.0)

    val firstBracket = innerBracket + log(x, eps, 3.0, lnFunc) - log(x,eps, 2.0, lnFunc)
    return firstBracket * log(x, eps, 5.0, lnFunc) + (log(x, eps, 2.0, lnFunc)/lnFunc(x,eps))
}

fun func(
        x: Double,
        eps: Double,
        sinFunc: (Double, Double) -> Double = ::sin,
        lnFunc: (Double, Double) -> Double = ::ln
) : Double
{
    return if (x <= 0.0) func1(x, eps, sinFunc) else func2(x, eps, lnFunc)
}