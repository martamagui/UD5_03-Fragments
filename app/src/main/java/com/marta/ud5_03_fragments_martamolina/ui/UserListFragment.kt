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
import android.widget.SearchView
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
    private lateinit var userList: MutableList<User>
    private var searchList: MutableList<User> = mutableListOf()
    private var random = ""


    private val adaptador = UserAdapter {
        viewChange(it.cell, it.name.first, "")
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
        binding.rvUsers.layoutManager = GridLayoutManager(context, 2)
        userList = (activity?.application as? App)?.userList!!
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                if (newText != null) {
                    filterQuery(newText)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filterQuery(newText)
                }
                return false
            }
        })
        binding.btnRandom.setOnClickListener{
            Toast.makeText(context, "Random Spaniard", Toast.LENGTH_SHORT).show()
            viewChange("","","random")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filterQuery(newText: String) {
        searchList.clear()
        val queryText = newText!!.lowercase()
        if (queryText.isNotEmpty()) {
            searchList = userList.filter { it ->
                it.name.first.lowercase().contains(newText.lowercase()) || it.name.last.contains(
                    queryText
                ) || it.name.first.contains(
                    queryText
                ) || it.name.last.contains(
                    queryText
                ) || codeToCountry(it.nat)?.contains(queryText) == true
            } as MutableList<User>
            adaptador.submitList(searchList)
            adaptador.notifyDataSetChanged()
        } else {
            adaptador.submitList(userList)
            adaptador.notifyDataSetChanged()
        }
    }

    private fun requestData() {
        val service = RandomUserApi.service.get500Users()
        val call = service.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("OnFailure", t.message.toString())
                Toast.makeText(context, R.string.error_msg, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) =
                if (response.isSuccessful) {
                    userList?.addAll(response.body()?.results!!)
                    adaptador.submitList(userList)
                    hideProgressBar()
                } else {
                    Toast.makeText(context, R.string.error_msg, Toast.LENGTH_SHORT).show()
                }
        })
    }

    private fun viewChange(cell: String, name: String, random: String) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(
            cell,
            name,
            random
        )
        findNavController().navigate(action)
    }

    private fun hideProgressBar() {
        binding.linearProgressIndicator.visibility = View.GONE
    }
}
