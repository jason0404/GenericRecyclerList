package com.example.genericrecyclerlist

import app.cash.turbine.turbineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `Expect some values to return`() = runBlocking {
        turbineScope {
            val turbine1 = flowOf(1).testIn(CoroutineScope(IO), name = "turbine 1")
            val turbine2 = flowOf(2).testIn(CoroutineScope(IO), name = "turbine 2")
            turbine1.awaitComplete()
            turbine2.awaitComplete()
        }
    }
}