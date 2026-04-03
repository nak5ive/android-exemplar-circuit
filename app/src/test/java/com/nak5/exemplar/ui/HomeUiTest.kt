package com.nak5.exemplar.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class HomeUiTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_9_PRO_XL,
        theme = "android:Theme.Material.Light.NoActionBar"
    )

    @Test
    fun preview() {
        paparazzi.snapshot {
            HomeUiPreview()
        }
    }
}