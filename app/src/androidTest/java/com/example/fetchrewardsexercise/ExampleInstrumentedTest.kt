package com.example.fetchrewardsexercise

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fetchrewardsexercise.api.FetchNameHelper

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.fetchrewardsexercise", appContext.packageName)
    }

    @Test
    fun useRightURL()
    {
        val baseURL = FetchNameHelper.baseURL
        assertEquals("https://fetch-hiring.s3.amazonaws.com" , baseURL)
    }
}