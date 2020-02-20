package kr.tripstore.proto.shared.domain.themecalendar

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kr.tripstore.proto.shared.data.calendar.CalendarsRepository
import kr.tripstore.proto.shared.data.calendar.FakeCalendarsRemoteDataSource
import kr.tripstore.proto.shared.data.temperature.FakeTemperaturesRemoteDataSource
import kr.tripstore.proto.shared.data.themecalendar.FakeThemeCalendarRemoteDataSource
import kr.tripstore.proto.shared.data.temperature.TemperaturesRepository
import kr.tripstore.proto.shared.data.themecalendar.ThemeCalendarRepository
import kr.tripstore.proto.shared.domain.calendar.GetLowestPriceCalendarUseCase
import kr.tripstore.proto.shared.result.Result
import kr.tripstore.proto.shared.test.util.assertResult
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetLowestPriceThemeCalendarUseCaseTest {

    private lateinit var calendarsRepository: CalendarsRepository
    private lateinit var temperaturesRepository: TemperaturesRepository
    private lateinit var themeCalendarRepository: ThemeCalendarRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        calendarsRepository = CalendarsRepository(FakeCalendarsRemoteDataSource())
        temperaturesRepository = TemperaturesRepository(FakeTemperaturesRemoteDataSource())
        themeCalendarRepository = ThemeCalendarRepository(FakeThemeCalendarRemoteDataSource())
    }

    @Test
    fun getLowestPriceThemeCalendar_isNotNull() =
        runBlockingTest {
            val fakeThemeCalendarId = 116
            val fakePlaceId = 9269
            val fakeCityIds = arrayOf(1)
            // Given repositories and GetLowestPriceCalendarUseCase which getting GetLowestPriceThemeCalendarUseCase
            val getLowestPriceCalendarUseCase = GetLowestPriceCalendarUseCase(
                calendarsRepository,
                temperaturesRepository,
                Dispatchers.Unconfined
            )
            val getLowestPriceThemeCalendarUseCase = GetLowestPriceThemeCalendarUseCase(
                getLowestPriceCalendarUseCase,
                themeCalendarRepository,
                Dispatchers.Unconfined
            )
            // When getting a result of LowestPriceThemeCalendar
            val getLowestPriceThemeCalendarResult = getLowestPriceThemeCalendarUseCase(
                fakeThemeCalendarId, fakePlaceId, fakeCityIds
            )
            // Then the result is not null
            assertResult(getLowestPriceThemeCalendarResult)
        }

    @Test
    fun getLowestPriceThemeCalendar_checkResultIsNotEmpty() =
        runBlockingTest {
            val fakeThemeCalendarId = 116
            val fakePlaceId = 9269
            val fakeCityIds = arrayOf(1)
            // Given repositories and GetLowestPriceCalendarUseCase which getting GetLowestPriceThemeCalendarUseCase
            val getLowestPriceCalendarUseCase = GetLowestPriceCalendarUseCase(
                calendarsRepository,
                temperaturesRepository,
                Dispatchers.Unconfined
            )
            val getLowestPriceThemeCalendarUseCase = GetLowestPriceThemeCalendarUseCase(
                getLowestPriceCalendarUseCase,
                themeCalendarRepository,
                Dispatchers.Unconfined
            )
            // When getting a result of LowestPriceThemeCalendar
            val getLowestPriceThemeCalendarResult = getLowestPriceThemeCalendarUseCase(
                fakeThemeCalendarId, fakePlaceId, fakeCityIds
            )
            val data = (getLowestPriceThemeCalendarResult as Result.Success).data
            // Then the data is not empty
            assertThat(data.years.size, IsNot.not(0))
            assertThat(data.years.first().months.size, IsNot.not(0))
        }

}