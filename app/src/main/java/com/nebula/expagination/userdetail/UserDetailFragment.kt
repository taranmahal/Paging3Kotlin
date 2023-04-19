package com.nebula.expagination.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.nebula.expagination.databinding.FragUserDetailBinding

class UserDetailFragment : Fragment() {

    lateinit var binding:FragUserDetailBinding
    private val args: UserDetailFragmentArgs by navArgs()
    lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragUserDetailBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, UserDetailViewModelFactory(args.userArg!!))[UserDetailViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
       //Toast.makeText(context, args.userArg?.phone, Toast.LENGTH_SHORT).show()

        return binding.root
    }
}

