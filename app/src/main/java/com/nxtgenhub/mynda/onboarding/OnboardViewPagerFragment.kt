package com.nxtgenhub.mynda.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.nxtgenhub.mynda.R
import com.nxtgenhub.mynda.databinding.FragmentOnboardViewPagerBinding
import com.nxtgenhub.mynda.onboarding.ViewPagerAdapter.Companion.MAX_STEP
import com.nxtgenhub.mynda.viewmodel.OnBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardViewPagerFragment : Fragment() {

    private var _binding: FragmentOnboardViewPagerBinding? = null
    private val bd get() = _binding!!

    private var navController: NavController? = null
    private val onBoardViewModel by viewModels<OnBoardViewModel>()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardViewPagerBinding.inflate(inflater, container, false)

        navController = findNavController()
        //val fragmentList = ArrayList<Fragment>()
        return bd.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBoardViewModel.onBoardingComplete.observe(viewLifecycleOwner) { completed ->
            if (completed){
                navController?.apply {
                    navigate(R.id.action_onboard_fragment_to_selectRoleFragment)
                }

            }
        }

        bd.viewPagerOnboard.adapter = ViewPagerAdapter()

        TabLayoutMediator(bd.onboardTabLayout, bd.viewPagerOnboard) {tab, position ->
        }.attach()

        bd.viewPagerOnboard.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == MAX_STEP - 1){
                    bd.btnNextOnboard.text = getString(R.string.get_started)
                } else {
                    bd.btnNextOnboard.text = getString(R.string.next)
                }
            }
        })

        bd.skipOnboard.setOnClickListener {
            onBoardViewModel.saveOnBoardState(true)
            navController?.navigate(R.id.action_onboard_fragment_to_selectRoleFragment)
        }

        bd.btnNextOnboard.setOnClickListener {
            if (bd.btnNextOnboard.text == getString(R.string.get_started)){
                onBoardViewModel.saveOnBoardState(true)
                navController?.navigate(R.id.action_onboard_fragment_to_selectRoleFragment)
            } else {
                val currentItem = bd.viewPagerOnboard.currentItem + 1
                bd.viewPagerOnboard.currentItem = currentItem

                if (currentItem >= MAX_STEP - 1){
                    bd.btnNextOnboard.text = getString(R.string.get_started)
                } else {
                    bd.btnNextOnboard.text = getString(R.string.next)
                }
            }

        }
    }
}