package br.com.mizaeldouglas.appcat.adpter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mizaeldouglas.appcat.R
import br.com.mizaeldouglas.appcat.databinding.ItemImageBinding
import br.com.mizaeldouglas.appcat.model.ImageData
import com.squareup.picasso.Picasso

class ImageAdapter(
    private val onItemClick: (ImageData) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var imageList = mutableListOf<ImageData>()

    fun addList(list: List<ImageData>) {
        this.imageList.clear()
        this.imageList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImageData) {
            val imageUrl = image.images.firstOrNull()?.link
            Log.d("ImageAdapter", "Loading image: $imageUrl")

            if (imageUrl != null) {
                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.imgCat)
            } else {
                Log.d("ImageAdapter", "No image available")
            }

            binding.root.setOnClickListener {
                onItemClick(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(
            layoutInflater, parent, false
        )
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = imageList[position]
        holder.bind(image)
    }
}