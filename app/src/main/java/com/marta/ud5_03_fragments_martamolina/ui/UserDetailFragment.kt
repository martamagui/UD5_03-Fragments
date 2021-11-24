package com.marta.ud5_03_fragments_martamolina.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.marta.ud5_03_fragments_martamolina.*
import com.marta.ud5_03_fragments_martamolina.databinding.FragmentUserDetailBinding
import com.marta.ud5_03_fragments_martamolina.model.User
import com.marta.ud5_03_fragments_martamolina.network.RandomUserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.marta.ud5_03_fragments_martamolina.model.Result


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
        if (args.random == "") {
            val userSent: User? =
                (activity?.application as App).userList.firstOrNull { args.cell == it.cell }
            userSent?.let { paintUser(it) }
        } else {
            randomRequest()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun paintUser(userSent: User) {
        if (userSent != null) {
            binding.ivDetail.imageUrl(userSent.picture.large)
            binding.tvDetailName.text =
                if (userSent.name != null) concatAndFormatFullName(userSent) else binding.tvDetailName.text
            val age: String? = (userSent.dob.age).toString()
            binding.tvAge.text =
                if ((userSent.dob.age).toString() != null) age else binding.tvAge.text
            binding.tvBirthDate.text =
                if (userSent.dob.date != null) formatDate(userSent.dob.date) else binding.tvBirthDate.text
            binding.tvDetailCountry.text =
                if (userSent.nat != null) codeToCountry(userSent.nat) else binding.tvDetailCountry.text
        }
    }

    private fun randomRequest() {
        val service = RandomUserApi.service.getRandomSpaniard()
        val call = service.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("OnFailure", t.message.toString())
                Toast.makeText(context, R.string.error_msg, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) =
                if (response.isSuccessful) {
                    val respuesta: List<User> = response.body()?.results!!
                    paintUser(respuesta.get(0))

                } else {
                    Toast.makeText(context, R.string.error_msg, Toast.LENGTH_SHORT).show()
                }
        })
    }
}