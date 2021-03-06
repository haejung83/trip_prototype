package kr.tripstore.proto.test.data

import kr.tripstore.proto.model.*
import kotlin.random.Random

@Suppress("UNUSED", "MemberVisibilityCanBePrivate")
object TestData {

    // Constants
    private const val EMPTY_STRING = ""
    private const val NO_ERROR = 0
    private const val TRIP_PACKAGE_BASE_ID = 100
    private const val TRIP_DETAIL_BASE_ID = 10000

    private val NO_ERRORS = null
    private val RESPONSE_TIME = Random.nextDouble(0.005, 0.9)

    // TripPackagePage
    val tripPackagePageFilter = TripPackagePageFilter("false", "false")
    val tripDetail = TripDetail(
        TRIP_DETAIL_BASE_ID + 1,
        "옥토끼",
        "https://pm1.narvii.com/6227/b25863e007813fdd18a2d4ebe0d624d37471f939_hq.jpg",
        "점프",
        EMPTY_STRING
    )
    val tripPackage = TripPackage(
        TRIP_PACKAGE_BASE_ID + 1,
        "달나라",
        "TIME_SALE",
        tripDetails = listOf(tripDetail)
    )
    val tripPackagePage = TripPackagePage(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        30,
        0,
        30,
        false,
        30,
        tripPackagePageFilter,
        listOf(tripPackage)
    )
    // Empty TripPackagePage
    val emptyTripPackage = TripPackage(
        TRIP_PACKAGE_BASE_ID + 0,
        "없음",
        "NONE",
        false,
        emptyList()
    )
    val emptyTripPackagePage = TripPackagePage(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        0,
        0,
        0,
        false,
        0,
        tripPackagePageFilter,
        emptyList()
    )
    // Calendars
    val calendarDays = (10..20).toList().map {
        CalendarDay("2020-01-$it", Random.nextInt(100_000, 900_000), false)
    }
    val calendars = Calendars(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        calendarDays
    )
    // Temperatures
    val temperatures = Temperatures(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        listOf(
            "최고 25℃",
            "최고 26℃",
            "최고 28℃",
            "최고 31℃",
            "최고 33℃",
            "최고 34℃",
            "최고 34℃",
            "최고 34℃",
            "최고 32℃",
            "최고 29℃",
            "최고 27℃",
            "최고 25℃"
        )
    )
    // ThemeCalendar
    val themeCalendarDescription = ThemeCalendarDescription(
        116,
        "보라카이",
        "화려한 여행\n보라카이에서 보내세요.",
        "보라카이 여행은 당신의 삶의 스트레스에서 탈출 할 기회입니다.",
        "",
        "2019-12-20 12:52:27",
        "2019-12-31 11:19:52"
    )
    val themeCalendar = ThemeCalendar(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        themeCalendarDescription
    )
    // Agency
    val agency = Agency(
        1,
        "HANA",
        "하나투어",
        "02-1600-5384",
        "상품번호와 여행사명이 있으면 빠른 상담이 가능합니다.\r\n트립스토어 운영시간은 평일 09:30 ~ 18:30 입니다!",
        "상품번호",
        "http://manager.hanatour.com",
        "https://page.tripstore.kr/strip/HANA.js"
    )
    val agencies = Agencies(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        listOf(agency)
    )
    // Airline
    val airline = Airline(
        1, "사천항공", "3U"
    )
    val airlines = Airlines(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        listOf(airline)
    )
    // Departure City
    val departureCity = DepartureCity(
        1, "JCN", "인천/김포"
    )
    val departureCities = DepartureCities(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        listOf(departureCity)
    )
    // Place
    val place = Place(
        479,
        "DAD",
        "다낭",
        85,
        "VN",
        "베트남",
        "아시아",
        "약 5시간 소요",
        false,
        1,
        ">다낭,DAD,ㄷㄴ,다 낭,다낭,다낭.,디닝"
    )
    val places = Places(
        RESPONSE_TIME,
        NO_ERROR,
        NO_ERRORS,
        listOf(place)
    )

}