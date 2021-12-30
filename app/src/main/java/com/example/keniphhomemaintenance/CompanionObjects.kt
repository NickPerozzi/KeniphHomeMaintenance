package com.example.keniphhomemaintenance

class CompanionObjects {

    companion object {
        fun getLightOrDarkIcon(isLight: Boolean): Int = if (isLight) R.drawable.ic_baseline_build_24_light else R.drawable.ic_baseline_build_24_dark
    }
}