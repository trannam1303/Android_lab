data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {
    operator fun plus(other: ComplexNumber): ComplexNumber = ComplexNumber(
            real = this.real + other.real,
            imag = this.imag + other.imag
    )

    operator fun minus(other: ComplexNumber): ComplexNumber = ComplexNumber(
            real = this.real - other.real,
            imag = this.imag - other.imag
    )

    operator fun times(other: ComplexNumber): ComplexNumber = ComplexNumber(
            real = (this.real * other.real - this.imag * other.imag),
            imag = (this.imag * other.real + this.real * other.imag)
    )

    operator fun div(other: ComplexNumber): ComplexNumber = ComplexNumber(
            real = (this.real * other.real + this.imag * other.imag) / (other.real.squared() + other.imag.squared()),
            imag = (this.imag * other.real - this.real * other.imag) / (other.real.squared() + other.imag.squared())
    )

    val abs: Double = Math.sqrt(this.real.squared() + this.imag.squared())

    fun conjugate(): ComplexNumber = ComplexNumber(this.real, -this.imag)
}

fun exponential(z: ComplexNumber): ComplexNumber =
        ComplexNumber(real = Math.exp(z.real)) * ComplexNumber(real = Math.cos(z.imag), imag = Math.sin(z.imag))


private fun Double.squared() = this * this