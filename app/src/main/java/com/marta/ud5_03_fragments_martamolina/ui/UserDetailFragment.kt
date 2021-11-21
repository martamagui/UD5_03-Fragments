package com.marta.ud5_03_fragments_martamolina.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.marta.ud5_03_fragments_martamolina.R
import com.marta.ud5_03_fragments_martamolina.databinding.FragmentUserDetailBinding
import com.marta.ud5_03_fragments_martamolina.App
import com.marta.ud5_03_fragments_martamolina.concatAndFormatFullName
import com.marta.ud5_03_fragments_martamolina.imageUrl
import com.marta.ud5_03_fragments_martamolina.model.User


class UserDetailFragment : Fragment() {
    private val args: UserDetailFragmentArgs by navArgs()
    private var _binding: FragmentUserDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userSent: User? =
            (activity?.application as App).userList.firstOrNull { args.cell == it.cell }
        if (userSent != null) {
            binding.imageView.imageUrl(userSent.picture.large)
            binding.tvDetailName.text =
                if (userSent.name != null) concatAndFormatFullName(userSent).toString() else binding.tvDetailName.text
            val age: String? = (userSent.dob.age).toString()
            binding.tvAge.text = age
            binding.tvBirthDate.text = userSent.dob.date
            binding.tvDetailCountry.text = userSent.nat
        }

    }
}