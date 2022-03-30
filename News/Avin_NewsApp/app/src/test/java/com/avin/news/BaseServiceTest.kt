package com.avin.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class BaseServiceTest {

    @Rule
    @JvmField
    var rule : TestRule = InstantTaskExecutorRule()

    @Before
    fun setUpRx(){
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

}
