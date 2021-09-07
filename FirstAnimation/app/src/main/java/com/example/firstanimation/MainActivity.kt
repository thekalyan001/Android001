package com.example.firstanimation

import android.animation.*
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.firstanimation.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rotateButton.setOnClickListener {
            rotater()
        }
        binding.translateButton.setOnClickListener {
            translator()
        }
        binding.scaleButton.setOnClickListener {
            scaler()
        }
        binding.fadeButton.setOnClickListener {
            fader()
        }
        binding.colorizeButton.setOnClickListener {
            colorizer()
        }
        binding.showerButton.setOnClickListener {
            shower()
        }

    }

    private fun shower() {
        val container= star.parent as ViewGroup //a reference to the star field ViewGroup (which is just the parent of the current star view).
        val containerW=container.width //the width and height of that container (which is just the parent of the current star view).
        val containerH= container.height
        var starW: Float = star.width.toFloat() //the default width and height of your star  (which you will later alter with a scale factor to get different-sized stars).
        var starH: Float = star.height.toFloat()

        //create a new View to hold the star graphic
        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_star)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        container.addView(newStar)

        //Modify the star to have a random size, from .1x to 1.6x of its default size

        newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
        newStar.scaleY = newStar.scaleX
        starW *= newStar.scaleX
        starH *= newStar.scaleY

        //Now position the new star. Horizontally, it should appear randomly somewhere from the left edge to the right edge.

        newStar.translationX = Math.random().toFloat() *
                containerW - starW / 2

        //Create animators to for star rotation and falling
        val mover = ObjectAnimator.ofFloat(
            newStar, View.TRANSLATION_Y,
            -starH, containerH + starH
        )
        mover.interpolator = AccelerateInterpolator(1f)

        //  rotation animation, the star will rotate a random amount between 0 and 1080 degrees

        val rotator = ObjectAnimator.ofFloat(
            newStar, View.ROTATION,
            (Math.random() * 1080).toFloat()
        )
        rotator.interpolator = LinearInterpolator()

        //Run the animations in parallel with AnimatorSet
        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 2500 + 500).toLong()

        //Once newStar has fallen off the bottom of the screen, it should be removed from the container.
       // Set a simple listener to wait for the end of the animation and remove it.

        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                container.removeViewAt(newStar)
            }
        })
        set.start()
    }

    private fun colorizer() { //color the star
        val animator = ObjectAnimator.ofArgb(
            star.parent,
            "backgroundColor", Color.BLACK, Color.RED
        )
        animator.duration = 500
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(colorizeButton)
        animator.start()
    }


    private fun fader() { // fade the star
        val animator= ObjectAnimator.ofFloat(star, View.ALPHA, 0f)
        animator.repeatCount=1
        animator.repeatMode= ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(fadeButton)
        animator.start()
    }

    private fun scaler() { //scaling the star in parallel
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(scaleButton)
        animator.start()
    }

    //create animation that moves the star to the right by 200 pixels
    private fun translator() {
        val animator = ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f)
        //we hvae translated the star to right but it will not come to it's original place so let's tell it to repeat the task after going
        //ek gya ek repeat kiy to aa gya apne jagah pe
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE

        animator.disableViewDuringAnimation(translateButton)
        animator.start()
    }

    //This code uses an adapter class (which provides default implementations of all of the
    //listener methods) instead of implementing the raw AnimatorListener interface
    private fun rotater() {
        //rotate ek baar -360 to 0
        //The reason that the animation starts at -360 is that that allows the star to complete a full circle (360 degrees) and end at 0
        val animator = ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f)
        animator.duration = 1000 //1000 millisecond
        animator.disableViewDuringAnimation(rotateButton) //disable the translateButton during the animation
        animator.start()
    }

    //function to disable the translate button during the animation
    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

}

private fun ViewGroup.removeViewAt(newStar: AppCompatImageView) {
    (newStar.parent as ViewGroup).removeView(newStar)
}

/*
https://developer.android.com/courses/kotlin-android-advanced/overview#lesson_2
REPETITION
Note: Repetition is a way of telling animations to do the same task again and again.
 You can specify how many times to repeat (or just tell it to run infinitely).
 You can also specify the repetition behavior, either REVERSE (for reversing the
 direction every time it repeats) or RESTART (for animating from the original start
 value to the original end value, thus repeating in the same direction every time).


 FADER
 Note: "Alpha" is a term generally used, especially in computer graphics, to denote
 the amount of opacity in an object. A value of 0 indicates that the object is completely
  transparent, and a value of 1 indicates that the object is completely opaque. View
  objects have a default value of 1. Animations that fade views in or out animate the
  alpha value between 0 and 1.

COLORIZE
Note: The ofArgb() method is the reason that this app builds against minSdk 21.
The rest of the functionality of the app can be run on earlier SDKs, but ofArgb()
was introduced in the Lollipop release. It is also possible to animate color values
on earlier releases, involving TypeEvaluators, and the use of ArgbEvaluator
specifically. This codelab uses ofArgb() instead for simplicity.

SHOWER
{
// se this https://developer.android.com/codelabs/advanced-android-kotlin-training-property-animation#8
For this effect, a button click will result in the creation of a star with a random size,
which will be added to the background container, just out of view of the top of that
container. The star will proceed to fall down to the bottom of the screen, accelerating
as it goes. As it falls, it will rotate.

 Important: You can, and should, use ObjectAnimator for all property animations in your
 application. There are other kinds of animations you can create in applications
 (including whole-application animation choreography, using MotionLayout), but for
 individual property animations, ObjectAnimator is the way to go.

Now position the new star. Horizontally, it should appear randomly somewhere from the
left edge to the right edge. This code uses the width of the star to position it from
half-way off the screen on the left (-starW / 2) to half-way off the screen on the right
(with the star positioned at (containerW - starW / 2).


}


  */