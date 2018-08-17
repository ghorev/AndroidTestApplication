package com.ghorev.testapplication.ui.first

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghorev.testapplication.R
import com.ghorev.testapplication.RecordViewModel
import com.ghorev.testapplication.model.Content
import com.ghorev.testapplication.databinding.ItemListRowBinding
import com.ghorev.testapplication.di.Injectable
import kotlinx.android.synthetic.main.fragment_first_page.view.*
import java.util.*
import javax.inject.Inject

class FirstPageFragment : Fragment(), Injectable {
    @Inject
    lateinit var content: Content

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first_page, container, false)
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = ListAdapter(content)
        return view
    }

    private class RowHolder(private val binding: ItemListRowBinding) :
            RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.viewModel = RecordViewModel(Content.Record("", Date()))
        }
        fun bind(record: Content.Record) {
            binding.viewModel!!.record = record
        }
    }

    private class ListAdapter(private val content: Content) : RecyclerView.Adapter<RowHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
            val binding: ItemListRowBinding =
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.item_list_row,
                            parent,
                            false)
            return RowHolder(binding)
        }

        override fun getItemCount(): Int = content.size

        override fun onBindViewHolder(holder: RowHolder, position: Int) {
            holder.bind(content.getRecord(position))
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstPageFragment()
    }
}
