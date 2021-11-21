package com.marta.ud5_03_fragments_martamolina.ui

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.marta.ud5_03_fragments_martamolina.*
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
    private lateinit var Activity : DashBoardActivity

    private val adaptador = UserAdapter {
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(
            it.cell,
            it.name.first
        )
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
        binding.rvUsers.layoutManager = GridLayoutManager(context,2)
        //TODO ¿Should I add dividers to the RV?
    }

    private fun requestData() {
        val service = RandomUserApi.service
        //TODO preguntar si esto está bien hecho con la application y la activity
        Activity = context as DashBoardActivity
        val app = Activity.application as App
        var userList = app.userList
        val call = service.get500Users().enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("OnFaliure", "(╯°□°）╯︵ ┻━┻")
                Log.d("OnFailure", t.message.toString())
                Toast.makeText(context, "faliure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) =
                if (response.isSuccessful) {
                    userList.addAll(response.body()?.results!!)
                    Log.d("recogido", app.userList[0].toString())
                    adaptador.submitList(userList)
                    binding.linearProgressIndicator.visibility = View.GONE
                } else {
                    Toast.makeText(context, "Error en la petición", Toast.LENGTH_SHORT).show()
                }
        })
    }
}