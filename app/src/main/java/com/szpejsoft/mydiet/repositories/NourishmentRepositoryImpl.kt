package com.szpejsoft.mydiet.repositories

import com.jakewharton.rxrelay2.BehaviorRelay
import com.szpejsoft.mydiet.dataproviders.NourishmentDataProvider
import com.szpejsoft.mydiet.dataproviders.SettingsDataProvider
import com.szpejsoft.mydiet.domain.EatenAllowedPortions
import com.szpejsoft.mydiet.domain.Nourishment
import com.szpejsoft.mydiet.domain.Portions
import com.szpejsoft.mydiet.domain.Settings
import com.szpejsoft.mydiet.utils.SchedulersFacade
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import org.joda.time.LocalDate
import javax.inject.Inject

class NourishmentRepositoryImpl
@Inject
constructor(private val settingsDataProvider: SettingsDataProvider,
            private val nourishmentDataProvider: NourishmentDataProvider,
            private val schedulersFacade: SchedulersFacade) : NourishmentRepository {
    override val fruitPortionsData: BehaviorRelay<Portions> = BehaviorRelay.create()
    override val vegetablePortionsData: BehaviorRelay<Portions> = BehaviorRelay.create()
    override val grainPortionsData: BehaviorRelay<Portions> = BehaviorRelay.create()
    override val dairyPortionsData: BehaviorRelay<Portions> = BehaviorRelay.create()
    override val meatPortionsData: BehaviorRelay<Portions> = BehaviorRelay.create()
    override val fatPortionsData: BehaviorRelay<Portions> = BehaviorRelay.create()
    override val nextMealIntervalData: BehaviorRelay<Int> = BehaviorRelay.create()

    private lateinit var date: LocalDate
    private lateinit var nourishment: Nourishment

    override fun setDate(date: LocalDate) {
        this.date = date
        Single.zip<Settings, Nourishment, EatenAllowedPortions>(settingsDataProvider.getSettingsForDate(date),
                nourishmentDataProvider.getNourishmentForDate(date),
                BiFunction { settings, nourishment ->
                    this.nourishment = nourishment
                    createEatenAllowedPortions(settings, nourishment)
                })
                .subscribeOn(schedulersFacade.computation())
                .observeOn(schedulersFacade.computation())
                .subscribeBy { sendData(it) }
    }


    private fun sendData(portions: EatenAllowedPortions?) {
        portions ?: return
        fruitPortionsData.accept(Portions(portions.fruitsConsumed, portions.fruitsMax))
        vegetablePortionsData.accept(Portions(portions.vegetablesConsumed, portions.vegetablesMax))
        grainPortionsData.accept(Portions(portions.grainConsumed, portions.grainMax))
        dairyPortionsData.accept(Portions(portions.dairyConsumed, portions.dairyMax))
        meatPortionsData.accept(Portions(portions.meatConsumed, portions.meatMax))
        fatPortionsData.accept(Portions(portions.fatConsumed, portions.fatMax))
    }

    private fun createEatenAllowedPortions(settings: Settings, nourishment: Nourishment): EatenAllowedPortions =
            EatenAllowedPortions(fruitsConsumed = nourishment.fruitsConsumed,
                    fruitsMax = settings.fruitPortionsAllowed,
                    vegetablesConsumed = nourishment.vegetablesConsumed,
                    vegetablesMax = settings.vegetablePortionsAllowed,
                    grainConsumed = nourishment.grainConsumed,
                    grainMax = settings.grainPortionsAllowed,
                    dairyConsumed = nourishment.dairyConsumed,
                    dairyMax = settings.dairyPortionsAllowed,
                    meatConsumed = nourishment.meatConsumed,
                    meatMax = settings.meatPortionsAllowed,
                    fatConsumed = nourishment.fatConsumed,
                    fatMax = settings.fatPortionsAllowed)


    override fun saveEatenFruitPortions(portions: Int) {
        nourishment = nourishment.copy(fruitsConsumed = portions)
        nourishmentDataProvider.save(nourishment)
    }

    override fun saveEatenVegetablePortions(portions: Int) {
        nourishment = nourishment.copy(vegetablesConsumed = portions)
        nourishmentDataProvider.save(nourishment)
    }

    override fun saveEatenGrainPortions(portions: Int) {
        nourishment = nourishment.copy(grainConsumed = portions)
        nourishmentDataProvider.save(nourishment)
    }

    override fun saveEatenDairyPortions(portions: Int) {
        nourishment = nourishment.copy(dairyConsumed = portions)
        nourishmentDataProvider.save(nourishment)
    }

    override fun saveEatenMeatPortions(portions: Int) {
        nourishment = nourishment.copy(meatConsumed = portions)
        nourishmentDataProvider.save(nourishment)
    }

    override fun saveEatenFatPortions(portions: Int) {
        nourishment = nourishment.copy(fatConsumed = portions)
        nourishmentDataProvider.save(nourishment)
    }
}