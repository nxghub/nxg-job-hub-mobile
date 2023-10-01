package com.nxtgenhub.mynda.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nxtgenhub.mynda.databinding.FragmentSelectRoleBinding
import com.nxtgenhub.mynda.extensions.launchActivity

class SelectRoleFragment : Fragment() {

    private var _binding: FragmentSelectRoleBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectRoleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.maidCardView.setOnClickListener {
            createAccount()
        }
        binding.agentCardView.setOnClickListener {
            createAccount()
        }
        binding.employerCardView.setOnClickListener {
            createAccount()
        }
        binding.labHospitalCardView.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        launchActivity<CreateAccountActivity> {  }
    }
}