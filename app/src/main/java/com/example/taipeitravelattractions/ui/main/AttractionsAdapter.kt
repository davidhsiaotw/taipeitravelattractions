package com.example.taipeitravelattractions.ui.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class AttractionsAdapter :
    PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(ATTRACTION_COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel?.let {
            (holder as AttractionViewHolder).bind((it as UiModel.AttractionItem).attraction)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttractionViewHolder.create(parent)
    }

    companion object {
        private val ATTRACTION_COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.AttractionItem && newItem is UiModel.AttractionItem
                        && oldItem.attraction.id == newItem.attraction.id)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}