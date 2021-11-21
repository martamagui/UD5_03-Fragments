package com.marta.ud5_03_fragments_martamolina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marta.ud5_03_fragments_martamolina.databinding.ItemUserBinding
import com.marta.ud5_03_fragments_martamolina.model.User
import com.marta.ud5_03_fragments_martamolina.ui.UserDetailFragment

import java.util.*

//TODO Implementar el UserAdapter a la RV. Crear Conexion con la api
class UserAdapter(private val onUserClicked: (User) -> Unit) :
    ListAdapter<User, UserAdapter.ViewHolder>(UserItemCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemUserBinding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.tvUserName.text =
            (user.name?.title)?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " " + (user.name?.first)?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } + " " + (user.name?.last)?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        holder.binding.tvCountry.text = codeToCountry(user.nat)
        holder.binding.ivUser.imageUrl(user.picture.medium)
        holder.binding.root.setOnClickListener { onUserClicked(user) }
    }

    inner class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

}



class UserItemCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}