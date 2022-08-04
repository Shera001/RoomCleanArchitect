package uz.crud.roomwithmvvm.presentation.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputLayout
import dagger.Lazy
import kotlinx.coroutines.launch
import uz.crud.domain.model.User
import uz.crud.domain.util.Resource
import uz.crud.roomwithmvvm.R
import uz.crud.roomwithmvvm.adapters.UserAdapter
import uz.crud.roomwithmvvm.adapters.UserItemDecoration
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

    private var bottomSheetBehavior: BottomSheetBehavior<LinearLayoutCompat>? = null

    private var isUpdate: Boolean = false
    private var user: User? = null

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
        viewModel.getUsers()
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.addUserDialog)
        bottomSheetBehavior?.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.bottomSheet.imageUp.rotation = 180 * slideOffset
            }
        })

        binding.bottomSheet.addBtn.setOnClickListener {
            val name = binding.bottomSheet.nameEt.text?.trim().toString()
            val phone = binding.bottomSheet.phoneEt.text?.trim().toString()
            if (isUpdate) {
                user?.let { item ->
                    item.name = name
                    item.phone = phone
                    viewModel.addUser(item, isUpdate)
                }
            } else {
                viewModel.addUser(User(name = name, phone = phone), isUpdate)
            }
        }
    }

    override fun onDestroyView() {
        bottomSheetBehavior = null
        _binding = null
        super.onDestroyView()
    }

    private fun initView() {
        binding.usersRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(UserItemDecoration(requireContext()))
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.result.collect { result: Resource ->
                    when (result) {
                        is Resource.Success -> onSuccess()
                        is Resource.Empty.NameEmpty -> setError(
                            binding.bottomSheet.nameLayout,
                            getString(R.string.empty_name)
                        )
                        is Resource.Empty.NumberEmpty -> setError(
                            binding.bottomSheet.numberLayout,
                            getString(R.string.empty_number)
                        )
                    }
                }
            }
        }
    }

    private fun onSuccess() {
        viewModel.getUsers()
        binding.bottomSheet.apply {
            nameEt.setText(getString(R.string.empty_message))
            phoneEt.setText(getString(R.string.empty_message))
        }
        if (isUpdate) {
            binding.bottomSheet.addBtn.text = getString(R.string.add)
            isUpdate = false
        }
        Toast.makeText(requireContext(), getString(R.string.done), Toast.LENGTH_SHORT).show()
    }

    private fun setError(textLayout: TextInputLayout, error: String) {
        textLayout.error = error
    }

    private val listener = object : OnUsersItemClickListener {
        override fun onUsersItemClick(item: User) {
            if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
            }
            binding.bottomSheet.apply {
                nameEt.setText(item.name)
                phoneEt.setText(item.phone)
                addBtn.text = getString(R.string.edit)
            }
            user = item
            isUpdate = true
        }

        override fun onFavoriteClick(item: User) {
            viewModel.updateItem(item)
        }
    }
}