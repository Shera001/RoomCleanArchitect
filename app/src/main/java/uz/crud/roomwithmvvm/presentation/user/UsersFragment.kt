package uz.crud.roomwithmvvm.presentation.user

import android.content.Context
import android.os.Bundle
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
import uz.crud.roomwithmvvm.app.MainApp
import uz.crud.roomwithmvvm.databinding.FragmentUsersBinding
import uz.crud.roomwithmvvm.listeners.OnUsersItemClickListener
import javax.inject.Inject

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val userAdapter: UserAdapter by lazy {
        UserAdapter(listener)
    }

    @Inject
    lateinit var usersViewModelFactory: Lazy<UsersViewModel.UserViewModelFactory>

    private val viewModel: UsersViewModel by viewModels {
        usersViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(fragment = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeToFlow()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initView() {
        binding.usersRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = userAdapter
        }
    }

    private fun subscribeToFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect { users: List<User> ->
                    userAdapter.submitList(users)
                }
            }
        }
    }

    private val listener = object : OnUsersItemClickListener {
        override fun onUsersItemClick(position: Int) {

        }
    }
}