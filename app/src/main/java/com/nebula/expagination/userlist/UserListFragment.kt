package com.nebula.expagination.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nebula.expagination.Repository
import com.nebula.expagination.api.RetrofitService
import com.nebula.expagination.databinding.FragUserListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class UserListFragment : Fragment() {

    lateinit var viewModel: UserViewModel
    lateinit var adapter: UserAdapter
    lateinit var binding: FragUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragUserListBinding.inflate(layoutInflater)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = Repository(retrofitService)
        adapter = UserAdapter(OnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToUserDetail(it)
            findNavController().navigate(action) // send user as argument
        })
        binding.recyclerview.adapter = adapter

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.layoutManager = layoutManager

        viewModel = ViewModelProvider(this, UserViewModelFactory(mainRepository))[UserViewModel::class.java]

       /* viewModel.userList.observe(viewLifecycleOwner) {
            // adapter.setUserList(it)
            adapter.submitList(it)
        }*/

      /*  viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
         viewModel.loading.observe(viewLifecycleOwner, Observer {
             if (it) {
                 binding.progressbar.visibility = View.VISIBLE
             } else {
                 binding.progressbar.visibility = View.GONE
             }
         })*/

       // viewModel.getUsers()
        lifecycleScope.launch {
            viewModel.getUsers()./*distinctUntilChanged().*/collectLatest {
                adapter.submitData(it)
            }
        }

        return binding.root
    }
}
