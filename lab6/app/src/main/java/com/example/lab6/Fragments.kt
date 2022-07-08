package com.example.lab6

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.lab6.databinding.FragmentLayoutBinding

class myDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).apply {
            setTitle("1871069")
            setMessage("김진웅")
            setPositiveButton("ok") {dialog, id->println("ok")}
        }.create()
    }
}

class homeFragment : Fragment(R.layout.fragment_layout){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding=FragmentLayoutBinding.bind(view)
        binding.textView.text="homeFragment"
    }
}

class page1Fragment : Fragment(R.layout.fragment_layout){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding=FragmentLayoutBinding.bind(view)
        binding.textView.text="Page1Fragment"
    }
}

class page2Fragment : Fragment(R.layout.fragment_layout){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding=FragmentLayoutBinding.bind(view)
        binding.textView.text="Page2Fragment"
    }
}