package ru.my.pet.splashScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_splash.view.*
import ru.my.pet.R


class SplashFragment : Fragment() {

    private lateinit var sideAnimation : Animation;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        sideAnimation = AnimationUtils.loadAnimation(context, R.anim.splash_slide_anim)
        val splashName = view.tv_splash_label
        splashName.setAnimation(sideAnimation)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
        }, 2000L)
        // Inflate the layout for this fragment
        return view
    }


}