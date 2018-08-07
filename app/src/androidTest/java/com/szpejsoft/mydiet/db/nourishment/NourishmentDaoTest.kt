package com.szpejsoft.mydiet.db.nourishment

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.szpejsoft.mydiet.db.MyDietDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NourishmentDaoTest {
    private lateinit var dao: NourishmentDao
    private lateinit var db: MyDietDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, MyDietDatabase::class.java).build()
        dao = db.nourishmentDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getFirstNourishmentDate_emptyDB_returnNothing() {
        //given
        // empty database
        //when
        val observer = dao.getFirstNourishmentDate().test()
        //then
        observer.assertComplete()
        observer.assertNoValues()
    }

    @Test
    fun getFirstNourishmentDate_oneEntityInDB_returnValue() {
        //given
        dao.insertOrUpdate(NourishmentEntity(date = "2017-09-03"))
        //when
        val observer = dao.getFirstNourishmentDate().test()
        //then
        observer.assertComplete()
        observer.assertValue("2017-09-03")
    }

    @Test
    fun getFirstNourishmentDate_coupleEntitiesInDB_returnValue() {
        //given
        dao.insertOrUpdate(NourishmentEntity(date = "2017-09-03"))
        dao.insertOrUpdate(NourishmentEntity(date = "2018-01-09"))
        dao.insertOrUpdate(NourishmentEntity(date = "2013-02-04"))
        dao.insertOrUpdate(NourishmentEntity(date = "2019-09-03"))
        //when
        val observer = dao.getFirstNourishmentDate().test()

        //then
        observer.assertComplete()
        observer.assertValue("2013-02-04")
    }

}