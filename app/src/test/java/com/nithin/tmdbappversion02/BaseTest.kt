package com.nithin.tmdbappversion02

import org.mockito.Mockito.mock

abstract class BaseTest {
    inline fun <reified T : Any> mock(): T = mock(T::class.java)

    inline fun <reified T : Any> getType(): Class<T> = T::class.java

}