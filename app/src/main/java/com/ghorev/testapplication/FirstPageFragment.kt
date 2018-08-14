package com.ghorev.testapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first_page.view.*
import kotlinx.android.synthetic.main.item_list_row.view.*
import javax.inject.Inject

class FirstPageFragment : Fragment() {
    @Inject
    lateinit var content: TableContent

    private var listener: OnFragmentInteractionListener? = null

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
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private class RowHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list_row, parent, false))
    {
        fun bind(row: TableContent.Record) {
            itemView.long_text.text = row.longText
            itemView.time_text.text = DateFormat.format("dd/MM/yyyy hh:mm:ss", row.time)
        }

    }

    private class ListAdapter(private val content: TableContent) : RecyclerView.Adapter<RowHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
            val inflater = LayoutInflater.from(parent.context)
            return RowHolder(inflater, parent)
        }

        override fun getItemCount(): Int = content.size

        override fun onBindViewHolder(holder: RowHolder, position: Int) {
            holder.bind(content.getRecord(position))
        }

    }

    interface OnFragmentInteractionListener

    companion object {
        @JvmStatic
        fun newInstance() = FirstPageFragment()
    }
}
