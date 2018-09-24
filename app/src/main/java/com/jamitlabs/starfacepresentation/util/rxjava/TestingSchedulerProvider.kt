package com.jamitlabs.starfacepresentation.util.rxjava

import io.reactivex.schedulers.TestScheduler

class TestingSchedulerProvider : SchedulerProvider {

    companion object {
        val TEST_SCHEDULER = TestScheduler()
    }

    override fun io() = TEST_SCHEDULER
    override fun ui() = TEST_SCHEDULER
    override fun computation() = TEST_SCHEDULER
}

