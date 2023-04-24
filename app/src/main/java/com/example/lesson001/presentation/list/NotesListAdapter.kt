package com.example.lesson001.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson001.data.Note
import com.example.lesson001.databinding.ItemNoteBinding

class NotesListAdapter : ListAdapter<Note, NotesListAdapter.NoteViewHolder>(diffUtil) {
    private var onNoteClick: (Note) -> Unit = {}
    private var onNoteLongClick: (Note) -> Unit = {}
    private var onSwipeToDeleteItem: (Note) -> Unit = {}

    fun setCallback(callback: (Note) -> Unit) {
        this.onNoteClick = callback
    }

    fun setCallbackLong(callback: (Note) -> Unit) {
        this.onNoteLongClick = callback
    }

    fun setCallbackSwipeToDelete(callback: (Note) -> Unit) {
        this.onSwipeToDeleteItem = callback

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
                root.setOnClickListener { onNoteClick(item) }

                root.setOnLongClickListener {
                    onNoteLongClick(item)
                    true
                }

                textViewText.text = item.text
            }
        }
    }
}

val diffUtil = object : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
}

