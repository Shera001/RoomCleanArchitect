package uz.crud.roomwithmvvm.presentation.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import kotlinx.coroutines.launch
import uz.crud.domain.model.User
import uz.crud.roomwithmvvm.adapters.UserAdapter
import uz.crud.roomwithmvvm.adapters.UserItemDecoration
import uz.crud.roomwithmvvm.app.MainApp
import uz.crud.roomwithmvvm.databinding.FragmentFavoriteBinding
import uz.crud.roomwithmvvm.listeners.OnUsersItemClickListener
import javax.inject.Inject

class FavoriteUsersFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val userAdapter: UserAdapter by lazy {
        UserAdapter(listener)
    }

    @Inject
    lateinit var viewModelFactory: Lazy<FavoriteUsersViewModel.FavoriteUsersViewModelFactory>
    private val viewModel: FavoriteUsersViewModel by viewModels {
        viewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeFlow()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteUsers()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        binding.favoriteUsersRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(UserItemDecoration(requireContext()))
            adapter = userAdapter
        }
    }

    private fun subscribeFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect { users: List<User> ->
                    Log.e("TAG", "subscribeFlow: $users")
                    userAdapter.submitList(users)
                }
            }
        }
    }

    private val listener = object : OnUsersItemClickListener {
        override fun onUsersItemClick(item: User) {

        }

        override fun onFavoriteClick(item: User) {

        }
    }
}