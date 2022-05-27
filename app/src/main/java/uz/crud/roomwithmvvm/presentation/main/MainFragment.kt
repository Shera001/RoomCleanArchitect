package uz.crud.roomwithmvvm.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import uz.crud.roomwithmvvm.R
import uz.crud.roomwithmvvm.adapters.MainPagerAdapter
import uz.crud.roomwithmvvm.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.mainViewPager.adapter = MainPagerAdapter(fragment = this)
        TabLayoutMediator(
            binding.mainTab, binding.mainViewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.apply {
                        text = getString(R.string.users)
                        icon = ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.ic_people
                        )
                    }
                }
                1 -> {
                    tab.apply {
                        text = getString(R.string.favorite)
                        icon = ContextCompat.getDrawable(
                            binding.root.context,
                            R.drawable.ic_baseline_favorite_24
                        )
                    }
                }
            }
        }
            .attach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}