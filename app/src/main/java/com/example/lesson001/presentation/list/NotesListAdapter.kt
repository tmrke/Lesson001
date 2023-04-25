package com.example.lesson001.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson001.data.Note
import com.example.lesson001.databinding.ItemNoteBinding
import javax.inject.Inject

class NotesListAdapter @Inject constructor() :
    ListAdapter<Note, NotesListAdapter.NoteViewHolder>(diffUtil) {

    private var onNoteClick: (Note) -> Unit = {}
    private var onSwipeToDeleteItem: (Note) -> Unit = {}
    fun setCallbackSwipeToDelete(callback: (Note) -> Unit) {
        this.onSwipeToDeleteItem = callback

    }

    fun setCallback(callback: (Note) -> Unit) {
        this.onNoteClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun delete(item: Note) {
        onSwipeToDeleteItem(item)
    }

    inner class NoteViewHolder(
        private val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note) {
            with(binding) {
                textViewText.text = item.text
                imageViewImage.setImageBitmap(item.bitmap)
            }
        }
    }
}

val diffUtil = object : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
}