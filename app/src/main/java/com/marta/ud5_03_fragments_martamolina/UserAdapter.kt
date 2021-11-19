package com.marta.ud5_03_fragments_martamolina

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marta.ud5_03_fragments_martamolina.databinding.ActivityDashboardBinding
import com.marta.ud5_03_fragments_martamolina.databinding.ItemUserBinding
import com.marta.ud5_03_fragments_martamolina.model.User
import java.util.*

class UserAdapter(
    private val onRepositoryClicked: (User) -> Unit,
    diffCallback: DiffUtil.ItemCallback<User>
) : androidx.recyclerview.widget.ListAdapter<User, UserAdapter.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemUserBinding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.tvUserName.text =
            (user.name.title).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } + " " + (user.name.first).replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } + " " + (user.name.last).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        holder.binding.tvCountry.text = user.nat
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