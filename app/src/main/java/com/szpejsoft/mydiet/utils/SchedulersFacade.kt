package com.szpejsoft.mydiet.utils

import io.reactivex.Scheduler

interface SchedulersFacade {

    fun computation(): Scheduler

    fun ui(): Scheduler
}