package com.qoiu.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qoiu.holybibleapp.R

class BibleAdapter(private val retry: BibleViewHolder.Retry) :
    RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    private val books = ArrayList<BookUi>()

    fun update(new: List<BookUi>) {
        books.clear()
        books.addAll(new)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (books[position]) {
        is BookUi.Testament -> 0
        is BookUi.Base -> 1
        is BookUi.Fail -> 2
        is BookUi.Progress -> 3
        else -> -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder =
        when (viewType) {
            0 -> BibleViewHolder.Base(R.layout.testament.makeView(parent))
            1 -> BibleViewHolder.Base(R.layout.book_layout.makeView(parent))
            2 -> BibleViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
            else -> BibleViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
        }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) =
        holder.bind(books[position])

    override fun getItemCount() = books.size

    abstract class BibleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(book: BookUi) {}

        class FullscreenProgress(view: View) : BibleViewHolder(view)

        class Base(view: View) : BibleViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)
            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        name.text = text
                    }
                })
            }
        }

        class Fail(view: View, private val retry: Retry) : BibleViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(book: BookUi) {
                book.map(object : BookUi.StringMapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                })
                button.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }

        interface Retry {
            fun tryAgain()
        }
    }
}

private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)