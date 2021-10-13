package com.qoiu.holybibleapp.presentation.verses

import android.view.View
import android.view.ViewGroup
import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.BaseAdapter
import com.qoiu.holybibleapp.core.BaseViewHolder
import com.qoiu.holybibleapp.core.CustomTextView
import com.qoiu.holybibleapp.core.Retry

class VersesAdapter(private val retry: Retry): BaseAdapter<VerseUi,BaseViewHolder<VerseUi>>() {
    override fun getItemViewType(position: Int): Int = when(list[position]){
        is VerseUi.Base->0
        is VerseUi.Fail->1
        is VerseUi.Progress->2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VerseUi> = when(viewType){
        0-> VersesViewHolder.Base(R.layout.text_layout.makeView(parent))
        1-> BaseViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent),retry)
        else->BaseViewHolder.FullscreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    abstract class VersesViewHolder(view: View): BaseViewHolder<VerseUi>(view){

        class Base(view: View):VersesViewHolder(view){
            private val textView = itemView.findViewById<CustomTextView>(R.id.textView)
            override fun bind(item: VerseUi) = item.map(textView)
        }
    }
}