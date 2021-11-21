package com.marta.ud5_03_fragments_martamolina.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marta.ud5_03_fragments_martamolina.App
import com.marta.ud5_03_fragments_martamolina.DashBoardActivity
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
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(
            it.cell,
            it.name.first
        )
        // TODO fix navigation: java.lang.IllegalStateException: View android.widget.FrameLayout{7b4f76c V.E...... ........ 0,0-1080,1895} does not have a NavController set
        findNavController().navigate(action)

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
                Log.d("OnFaliure", "(╯°□°）╯︵ ┻━┻")
                Log.d("OnFailure", t.message.toString())
                Toast.makeText(context, "faliure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) =
                if (response.isSuccessful) {
                    var userList = (activity?.application as App).userList
                    userList = response.body()?.results!!
                    adaptador.submitList(userList)
                } else {
                    Toast.makeText(context, "Error en la petición", Toast.LENGTH_SHORT).show()
                }
        })
    }
}