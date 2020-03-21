package com.mapbox.navigation.ui.utils

import android.content.Context
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.navigation.base.typedef.IMPERIAL
import com.mapbox.navigation.base.typedef.METRIC
import com.mapbox.navigation.base.typedef.VoiceUnit
import com.mapbox.navigation.utils.extensions.inferDeviceLocale
import java.util.Locale

object LocaleEx {
    /**
     * Returns the unit type for the specified locale. Try to avoid using this unnecessarily because
     * all methods consuming unit type are able to handle the NONE_SPECIFIED type
     *
     * @return unit type for specified locale
     */
    @JvmStatic
    @VoiceUnit
    fun getUnitTypeForLocale(locale: Locale): String {
        return when(locale.country.toUpperCase(locale)) {
            "US" -> IMPERIAL
            "LR" -> IMPERIAL
            "MM" -> IMPERIAL
            else -> METRIC
        }
    }

    @JvmStatic
    fun getLocaleDirectionsRoute(directionsRoute: DirectionsRoute, context: Context): Locale {
        return if (directionsRoute.voiceLanguage() != null) {
            Locale(directionsRoute.voiceLanguage())
        }
        else {
            context.inferDeviceLocale()
        }
    }
}