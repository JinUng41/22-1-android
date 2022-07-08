package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.lab5.databinding.*


class HomeFragment : Fragment(R.layout.home_fragment){
    private val viewModel:MyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=HomeFragmentBinding.bind(view)
        binding.button.setOnClickListener {
            viewModel.increase()
            findNavController().navigate(R.id.action_homeFragment_to_nav1Fragment)
        }
        viewModel.MyLiveData.observe(viewLifecycleOwner){
            binding.textView.text="$it"
        }
    }
}

class Nav1Fragment : Fragment(R.layout.nav1_fragment) {
    private val viewModel:MyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=Nav1FragmentBinding.bind(view)
        binding.button.setOnClickListener {
            viewModel.increase()
            findNavController().navigate(R.id.action_nav1Fragment_to_nav2Fragment)
        }
        viewModel.MyLiveData.observe(viewLifecycleOwner){
            binding.textView.text="$it"
        }
    }
}

class Nav2Fragment:Fragment(R.layout.nav2_fragment) {
    private val viewModel:MyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=Nav2FragmentBinding.bind(view)
        binding.button.setOnClickListener {
            viewModel.increase()
            findNavController().navigate(R.id.action_nav2Fragment_to_homeFragment)
        }
        viewModel.MyLiveData.observe(viewLifecycleOwner){
            binding.textView.text="$it"
        }
    }
}

class MainActivity : AppCompatActivity() {
    private val viewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}