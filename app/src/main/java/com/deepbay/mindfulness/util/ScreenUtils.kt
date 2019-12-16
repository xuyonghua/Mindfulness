package com.deepbay.mindfulness.util

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources

object ScreenUtils {
    /**
     * Adapt the screen for vertical slide.
     *
     * @param activity        The activity.
     * @param designWidthInPx The size of design diagram's width, in pixel.
     */
    fun adaptScreen4VerticalSlide(
        activity: Activity,
        designWidthInPx: Int
    ) {
        adaptScreen(activity, designWidthInPx, true)
    }

    /**
     * Return whether screen is portrait.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isPortrait(activity: Activity): Boolean {
        return (activity.application.resources.configuration.orientation
                == Configuration.ORIENTATION_PORTRAIT)
    }

    /**
     * Adapt the screen for horizontal slide.
     *
     * @param activity         The activity.
     * @param designHeightInPx The size of design diagram's height, in pixel.
     */
    fun adaptScreen4HorizontalSlide(
        activity: Activity,
        designHeightInPx: Int
    ) {
        adaptScreen(activity, designHeightInPx, false)
    }

    /**
     * Reference from: https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
     */
    private fun adaptScreen(
        activity: Activity,
        sizeInPx: Int,
        isVerticalSlide: Boolean
    ) {
        val systemDm = Resources.getSystem().displayMetrics
        val appDm = activity.application.resources.displayMetrics
        val activityDm = activity.resources.displayMetrics
        if (isVerticalSlide) {
            activityDm.density = activityDm.widthPixels / sizeInPx.toFloat()
        } else {
            activityDm.density = activityDm.heightPixels / sizeInPx.toFloat()
        }
        activityDm.scaledDensity = activityDm.density * (systemDm.scaledDensity / systemDm.density)
        activityDm.densityDpi = (160 * activityDm.density).toInt()
        appDm.density = activityDm.density
        appDm.scaledDensity = activityDm.scaledDensity
        appDm.densityDpi = activityDm.densityDpi
    }

    /**
     * Cancel adapt the screen.
     *
     * @param activity The activity.
     */
    fun cancelAdaptScreen(activity: Activity) {
        val systemDm = Resources.getSystem().displayMetrics
        val appDm = activity.application.resources.displayMetrics
        val activityDm = activity.resources.displayMetrics
        activityDm.density = systemDm.density
        activityDm.scaledDensity = systemDm.scaledDensity
        activityDm.densityDpi = systemDm.densityDpi
        appDm.density = systemDm.density
        appDm.scaledDensity = systemDm.scaledDensity
        appDm.densityDpi = systemDm.densityDpi
    }

    /**
     * Return whether adapt screen.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isAdaptScreen(activity: Activity): Boolean {
        val systemDm = Resources.getSystem().displayMetrics
        val appDm = activity.application.resources.displayMetrics
        return systemDm.density != appDm.density
    }

    val statusBarHeight: Int
        get() {
            val resources = Resources.getSystem()
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }
}