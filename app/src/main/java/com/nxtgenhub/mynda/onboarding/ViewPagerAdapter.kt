package com.nxtgenhub.mynda.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nxtgenhub.mynda.R
import com.nxtgenhub.mynda.databinding.FragmentFirstBinding


class ViewPagerAdapter : RecyclerView.Adapter<PagerVH2>() {

    companion object{
        const val MAX_STEP = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH2 {
        return PagerVH2(
            FragmentFirstBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = MAX_STEP

    //binding the screen with view
    override fun onBindViewHolder(holder: PagerVH2, position: Int) = holder.itemView.run {

        with(holder) {
            if (position == 0) {
                binding.onboardTitleTextview.text = context.getString(R.string.first_title_onboard)
                binding.onboardDescriptionTextview.text = context.getString(R.string.first_description_onboard)
                binding.onboardImageview.setImageResource(R.drawable.first_onboard_image)
            }
            if (position == 1) {
                binding.onboardTitleTextview.text = context.getString(R.string.second_title_onboard)
                binding.onboardDescriptionTextview.text = context.getString(R.string.second_description_onboard)
                binding.onboardImageview.setImageResource(R.drawable.second_onboard_image)
            }
            if (position == 2) {
                binding.onboardTitleTextview.text = context.getString(R.string.third_title_onboard)
                binding.onboardDescriptionTextview.text = context.getString(R.string.third_description_onboard)
                binding.onboardImageview.setImageResource(R.drawable.third_onboard_image)
            }
        }
    }
}
class PagerVH2(val binding: FragmentFirstBinding) : RecyclerView.ViewHolder(binding.root)


/*
class ViewPagerAdapter(
    listFragment: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fm, lifecycle) {

    private val fragmentList = listFragment

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}*/
