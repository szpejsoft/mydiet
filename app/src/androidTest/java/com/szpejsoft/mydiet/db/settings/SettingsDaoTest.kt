package com.szpejsoft.mydiet.db.settings

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.szpejsoft.mydiet.db.MyDietDatabase
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsDaoTest {
    lateinit var dao: SettingsDao
    lateinit var db: MyDietDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, MyDietDatabase::class.java).build()
        dao = db.settingsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getSettings_noSettingsInDB_nothingReturned() {
        //GIVEN
        //WHEN
        val observer = dao.getSettingsForDate("2017-12-31").test()
        //THEN
        observer.assertComplete()
        observer.assertNoValues()
    }

    @Test
    fun getSettings_settingsWithSameDateInDB_appropriateSettingsReturned() {
        //GIVEN
        val settings = SettingsEntity(date = "2018-03-28", fatPortionsAllowed = 7)
        dao.insert(settings)
        //WHEN
        val observer = dao.getSettingsForDate("2018-03-28").test()

        observer.assertComplete()
        observer.assertValue(settings)
    }

    @Test
    fun getSettings_settingsWithLaterDateInDB_noSettingsReturned() {
        //GIVEN
        val settings = SettingsEntity(date = "2018-03-28", fatPortionsAllowed = 7)
        dao.insert(settings)
        //WHEN
        val observer = dao.getSettingsForDate("2018-01-28").test()
        //THEN
        observer.assertComplete()
        observer.assertNoValues()
    }

    @Test
    fun getSettings_settingsWithPreviousDateInDB_appropriateSettingsReturned() {
        //GIVEN
        val settings = SettingsEntity(date = "2018-01-28", fatPortionsAllowed = 7)
        dao.insert(settings)
        //WHEN
        val observer = dao.getSettingsForDate("2018-03-28").test()
        //THEN
        observer.assertComplete()
        observer.assertValue(settings)
    }

    @Test
    fun getSettings_twoSettingsInDB_queryWithDateBetween_appropriateSettingsReturned() {
        //GIVEN
        val settings1 = SettingsEntity(date = "2018-01-28", fatPortionsAllowed = 1)
        val settings2 = SettingsEntity(date = "2018-03-28", fatPortionsAllowed = 3)
        dao.insert(settings1)
        dao.insert(settings2)
        //WHEN
        val observer = dao.getSettingsForDate("2018-02-28").test()
        //THEN
        observer.assertComplete()
        observer.assertValue(settings1)
    }

    @Test
    fun insertSettingsWithSameDate_settingsInDBUpdated() {
        //GIVEN
        val settings1 = SettingsEntity(date = "2018-01-28", fatPortionsAllowed = 1)
        val settings2 = SettingsEntity(date = "2018-01-28", fatPortionsAllowed = 3)
        dao.insert(settings1)
        dao.insert(settings2)
        //WHEN
        val observer = dao.getSettingsForDate("2018-02-28").test()
        //THEN
        observer.assertComplete()
        observer.assertValue(settings2)
    }


}