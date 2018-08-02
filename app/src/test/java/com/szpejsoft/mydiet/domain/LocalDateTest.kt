package com.szpejsoft.mydiet.domain

import org.joda.time.Days
import org.joda.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test


class LocalDateTest {

    @Test
    fun dateToStringTest() {
        val date = LocalDate(2018, 1, 31)
        assertEquals("2018-01-31", date.toString(DATE_FORMAT))
    }

    @Test
    fun stringToDateTest() {
        val fromString = LocalDate("2018-01-31")
        assertEquals(LocalDate(2018, 1, 31), fromString)
    }

    @Test
    fun bla() {
        val date1 = LocalDate(2018, 1, 31)
        val date2 = LocalDate(2018, 2, 1)
        assertFalse(date1.isAfter(date2))
        assertEquals(-1,Days.daysBetween(date2, date1).days)
        assertEquals(1,Days.daysBetween(date1, date2).days)

    }


}