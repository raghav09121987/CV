package util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {

    fun <T> getValue(liveData: LiveData<T>): T {

        val data = arrayOfNulls<Any>(1)

        // latch for blocking thread until data is set
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown() // release the latch
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS) // wait for onChanged to fire and set data

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }
}