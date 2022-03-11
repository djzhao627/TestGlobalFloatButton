package cn.djzhao.testglobalfloatbutton

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast

/**
 * Application
 *
 * @author djzhao
 * @date 22/03/03
 */
class MyApplication : Application() {

    private val TAG = "MyApplication"

    private lateinit var layoutParams: FrameLayout.LayoutParams
    private lateinit var eventTouchListener: View.OnTouchListener
    private var previousX = 0f;
    private var previousY = 100f;

    override fun onCreate() {
        super.onCreate()

        initTouchEvent()
        initLayoutParams()
        registerActivityLifecycleCallbacks()
    }

    private fun initLayoutParams() {
        layoutParams = FrameLayout.LayoutParams(
            80,
            80,
            Gravity.START)

        layoutParams.marginStart = previousX.toInt()
        layoutParams.topMargin = previousY.toInt()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initTouchEvent() {
        eventTouchListener = View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    previousX = event.rawX
                    previousY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    layoutParams.marginStart += (event.rawX - previousX).toInt()
                    layoutParams.topMargin += (event.rawY - previousY).toInt()
                    val parent = v.parent as ViewGroup
                    parent.updateViewLayout(v, layoutParams)

                    previousX = event.rawX
                    previousY = event.rawY
                }
            }
            false
        }
    }


    private fun registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(TAG, "onActivityStarted: ${activity.localClassName}")
                if (activity is FloatingButtonInterface) {
                    (activity.window.decorView as ViewGroup).addView(getFloatingLayout(),
                        layoutParams)
                }
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(TAG, "onActivityResumed: ${activity.localClassName}")
            }

            override fun onActivityPaused(activity: Activity) {
                Log.d(TAG, "onActivityPaused: ${activity.localClassName}")
                // (activity.window.decorView as ViewGroup).removeView(floatingLayout)
            }

            override fun onActivityPostPaused(activity: Activity) {
                Log.d(TAG, "onActivityPostPaused: ${activity.localClassName}")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(TAG, "onActivityStarted: ${activity.localClassName}")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.d(TAG, "onActivitySaveInstanceState: ${activity.localClassName}")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(TAG, "onActivityDestroyed: ${activity.localClassName}")
            }
        })
    }

    private fun getFloatingLayout(): RelativeLayout {
        val floatingLayout = LayoutInflater.from(this)
            .inflate(R.layout.layout_floating_button, null) as RelativeLayout

        floatingLayout.setOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }

        floatingLayout.setOnTouchListener(eventTouchListener)
        return floatingLayout
    }

}