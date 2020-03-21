package com.mapbox.navigation.ui.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.navigation.base.typedef.IMPERIAL
import com.mapbox.navigation.base.typedef.METRIC
import com.mapbox.navigation.utils.extensions.inferDeviceLocale
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import java.util.Locale

class LocaleExTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun initialize() {
            mockkStatic("com.mapbox.navigation.utils.extensions.ContextEx")
        }
    }

    @Test
    fun getUnitTypeForLocaleCountryUS() {
        val locale = mockk<Locale>()
        every { locale.country } returns "us"
        every { locale.language } returns "spanglish"

        val result = LocaleEx.getUnitTypeForLocale(locale)

        assertEquals(IMPERIAL, result)
    }

    @Test
    fun getUnitTypeForLocaleCountryLR() {
        val locale = mockk<Locale>()
        every { locale.country } returns "lr"
        every { locale.language } returns "spanglish"

        val result = LocaleEx.getUnitTypeForLocale(locale)

        assertEquals(IMPERIAL, result)
    }

    @Test
    fun getUnitTypeForLocaleCountryMM() {
        val locale = mockk<Locale>()
        every { locale.country } returns "mm"
        every { locale.language } returns "spanglish"

        val result = LocaleEx.getUnitTypeForLocale(locale)

        assertEquals(IMPERIAL, result)
    }

    @Test
    fun getUnitTypeForLocaleCountryDefault() {
        val locale = mockk<Locale>()
        every { locale.country } returns "zz"
        every { locale.language } returns "spanglish"

        val result = LocaleEx.getUnitTypeForLocale(locale)

        assertEquals(METRIC, result)
    }

    @Test
    fun getLocaleDirectionsRouteNonNullVoiceLanguage() {
        val context = mockk<Context>()
        val directionsRoute = mockk<DirectionsRoute>()
        every { directionsRoute.voiceLanguage() } returns "en"

        val result = LocaleEx.getLocaleDirectionsRoute(directionsRoute, context)

        assertEquals("en", result.language)
    }

    @Test
    fun getLocaleDirectionsRouteNullVoiceLanguage() {
        val context = mockk<Context>()
        val resources = mockk<Resources>()
        val configuration = mockk<Configuration>()
        val directionsRoute = mockk<DirectionsRoute>()
        every { directionsRoute.voiceLanguage() } returns null
        every { context.resources } returns resources
        every { resources.configuration } returns configuration
        every { configuration.locale } returns Locale("en")
        every { context.inferDeviceLocale() } returns Locale("en")

        val result = LocaleEx.getLocaleDirectionsRoute(directionsRoute, context)

        assertEquals("en", result.language)
    }
}