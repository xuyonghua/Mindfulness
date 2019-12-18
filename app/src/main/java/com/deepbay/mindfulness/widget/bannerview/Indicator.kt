package com.deepbay.mindfulness.widget.bannerview

interface Indicator {
    fun onViewSelected(position: Int)
    fun onScrolled(dx: Int,ratio : Float)
}