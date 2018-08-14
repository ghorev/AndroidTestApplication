package com.ghorev.testapplication

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghorev.testapplication.databinding.ItemListRowBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first_page.view.*
import java.util.*
import javax.inject.Inject

class FirstPageFragment : Fragment() {
    @Inject
    lateinit var content: TableContent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first_page, container, false)
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = ListAdapter(content)
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private class RowHolder(private val binding: ItemListRowBinding) :
            RecyclerView.ViewHolder(binding.root)
    {
        init {
            binding.viewModel = RecordViewModel(TableContent.Record("", Date()))
        }
        fun bind(record: TableContent.Record) {
            binding.viewModel!!.record = record
        }
    }

    private class ListAdapter(private val content: TableContent) : RecyclerView.Adapter<RowHolder>() {
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
