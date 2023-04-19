package com.nebula.expagination.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nebula.expagination.databinding.FragUserItemBinding
import com.nebula.expagination.userlist.data.Result


class UserAdapter(private val onClickListener: OnClickListener) : PagingDataAdapter<Result, UserAdapter.MyViewHolder>(MyDiffUtil)/*ListAdapter*/
  {
    companion object MyDiffUtil : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: FragUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Result?) {
            Glide.with(binding.userImage)
                .load(user?.picture?.thumbnail)
                .into(binding.userImage)

            binding.username.text = user?.name?.first
            binding.country.text = user?.location?.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FragUserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(user!!)
        }
        holder.bind(user)
    }
}

class OnClickListener(val clickListener: (result: Result) -> Unit) {
    fun onClick(result: Result) = clickListener(result)
}

//   var users = mutableListOf<Result>()

/*  fun setUserList(users: List<Result>) {
      this.users = users.toMutableList()
      notifyDataSetChanged()
  }
*/
/*  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val inflater = LayoutInflater.from(parent.context)

      val binding = FragUserItemBinding.inflate(inflater, parent, false)
      return MyViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val user = users[position]
      holder.binding.username.text = user.name.first
      Glide.with(holder.itemView.context).load(user.picture.thumbnail).placeholder(R.drawable.fireplace)
          .into(holder.binding.userImage)

  }

  override fun getItemCount(): Int {
      return users.size
  }
  //class MyViewHolder(val binding: FragUserItemBinding) : RecyclerView.ViewHolder(binding.root) {}
*/