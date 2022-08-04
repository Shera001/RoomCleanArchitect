package uz.crud.roomwithmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.crud.domain.model.User
import uz.crud.roomwithmvvm.R
import uz.crud.roomwithmvvm.databinding.ItemUserBinding
import uz.crud.roomwithmvvm.listeners.OnUsersItemClickListener

class UserAdapter(
    private val listener: OnUsersItemClickListener
) : ListAdapter<User, UserAdapter.ViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }


    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: User, listener: OnUsersItemClickListener) {
            binding.apply {
                nameTv.text = item.name
                phoneTv.text = item.phone
                if (item.isFavorite) {
                    favoriteIv.setImageResource(R.drawable.ic_favorite)
                } else {
                    favoriteIv.setImageResource(R.drawable.ic_favorite_border)
                }
                root.setOnClickListener {
                    listener.onUsersItemClick(item)
                }
                favoriteIv.setOnClickListener {
                    if (item.isFavorite) {
                        favoriteIv.setImageResource(R.drawable.ic_favorite_border)
                    } else {
                        favoriteIv.setImageResource(R.drawable.ic_favorite)
                    }

                    listener.onFavoriteClick(item)
                }
            }
        }

        companion object {
            fun create(container: ViewGroup): ViewHolder {
                return ViewHolder(
                    ItemUserBinding.inflate(
                        LayoutInflater.from(container.context),
                        container,
                        false
                    )
                )
            }
        }
    }

    companion object DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            (oldItem.id == newItem.id)

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            (oldItem == newItem)
    }
}