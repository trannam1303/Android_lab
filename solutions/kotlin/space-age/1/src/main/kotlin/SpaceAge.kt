import SpaceAge.Planet.*
import java.math.BigDecimal
import java.math.RoundingMode

private const val SECONDS_IN_EARTH_YEAR = 31_557_600

class SpaceAge(private val ageInSeconds: Long) {
    fun onEarth() = ageInSeconds.toYearsOnPlanet(Earth)
    fun onMercury() = ageInSeconds.toYearsOnPlanet(Mercury)
    fun onVenus() = ageInSeconds.toYearsOnPlanet(Venus)
    fun onMars() = ageInSeconds.toYearsOnPlanet(Mars)
    fun onJupiter() = ageInSeconds.toYearsOnPlanet(Jupiter)
    fun onSaturn() = ageInSeconds.toYearsOnPlanet(Saturn)
    fun onUranus() = ageInSeconds.toYearsOnPlanet(Uranus)
    fun onNeptune() = ageInSeconds.toYearsOnPlanet(Neptune)

    private enum class Planet(val earthYearsPerYear: Double) {
        Earth(1.0),
        Mercury(0.2408467),
        Venus(0.61519726),
        Mars(1.8808158),
        Jupiter(11.862615),
        Saturn(29.447498),
        Uranus(84.016846),
        Neptune(164.79132),
    }

    private fun Long.toYearsOnPlanet(planet: SpaceAge.Planet) =
            BigDecimal(this / (planet.earthYearsPerYear * SECONDS_IN_EARTH_YEAR)).setScale(2, RoundingMode.HALF_EVEN).toDouble()
}