package com.judahben149.motiontraction.domain.scheduler

import io.reactivex.Scheduler

class TestSchedulerProvider(private val scheduler: Scheduler): SchedulerProvider {

    override fun io() = scheduler
    override fun ui() = scheduler
    override fun computation() = scheduler
}