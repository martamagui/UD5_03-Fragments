package com.marta.ud5_03_fragments_martamolina.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.marta.ud5_03_fragments_martamolina.R
import com.marta.ud5_03_fragments_martamolina.UserAdapter
import com.marta.ud5_03_fragments_martamolina.model.Result
import com.marta.ud5_03_fragments_martamolina.databinding.FragmentUserListBinding
import com.marta.ud5_03_fragments_martamolina.model.User
import com.marta.ud5_03_fragments_martamolina.network.RandomUserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

    private val adaptador = UserAdapter {
        val fg = UserDetailFragment.newInstance()
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.container, fg)
            .addToBackStack("User")
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestData()
        binding.rvUsers.adapter = adaptador
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        //TODO ¿Should I add dividers to the RV?
    }

    private fun requestData() {
        val service = RandomUserApi.service
        val call = service.get500Users().enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("OnFaliure", "Failed request")
                Log.d("OnFailure", t.message.toString())
                Toast.makeText(context, "faliure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) =
                if (response.isSuccessful) {
                    Toast.makeText(context, "todo ok", Toast.LENGTH_SHORT).show()
                    val UserList: List<User>? = response.body()?.results
                    adaptador.submitList(UserList)
                } else {
                    Toast.makeText(context, "Error en la petición", Toast.LENGTH_SHORT).show()
                }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}