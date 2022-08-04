package uz.crud.roomwithmvvm.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.crud.roomwithmvvm.presentation.favorite.FavoriteUsersFragment
import uz.crud.roomwithmvvm.presentation.user.UsersFragment

class MainPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> UsersFragment()
            1 -> FavoriteUsersFragment()
            else -> UsersFragment()
        }

}