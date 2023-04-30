package com.example.lesson001.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.lesson001.data.Note
import com.example.lesson001.databinding.ItemNoteBinding

class NoteViewHolder(
    private val binding: ItemNoteBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Note) {
        with(binding) {
            textViewText.text = item.text
            imageViewImage.setImageBitmap(item.bitmap)
        }
    }
}