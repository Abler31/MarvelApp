package ru.my.pet.screens.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.my.pet.screens.viewpager.first.FirstFragment
import ru.my.pet.screens.viewpager.second.SecondFragment

class PagerAdapter(fragmentActivity: ViewPagerFragment): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0-> FirstFragment()
            else-> SecondFragment()
        }
    }
}