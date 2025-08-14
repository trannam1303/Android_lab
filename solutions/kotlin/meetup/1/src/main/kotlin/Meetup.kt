import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.Year

data class Meetup(private val month: Int, private val year: Int) {
    fun day(dayOfWeek: DayOfWeek, meetupSchedule: MeetupSchedule): LocalDate =
            meetupSchedule.asRange(month, year)
                    .map { LocalDate.of(year, month, it) }
                    .first { it.dayOfWeek == dayOfWeek }
}

private fun MeetupSchedule.asRange(month: Int, year: Int): IntProgression = when (this) {
    MeetupSchedule.FIRST -> 1..7
    MeetupSchedule.SECOND -> 8..14
    MeetupSchedule.TEENTH -> 13..19
    MeetupSchedule.THIRD -> 15..21
    MeetupSchedule.FOURTH -> 22..28
    MeetupSchedule.LAST -> Month.of(month).length(Year.of(year).isLeap).let { it downTo it - 7}
}