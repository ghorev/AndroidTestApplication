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
import kotlinx.android.synthetic.main.fragment_first_page.view.*
import kotlinx.android.synthetic.main.item_list_row.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FirstPageFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FirstPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FirstPageFragment : Fragment() {
    private class Row(val longText: String, val time: Date)

    private class Model {
        private var data = mutableListOf<Row>()

        init {
            val date = Date()
            for (i in 1..50)
                data.add(Row((1..i).joinToString(" - "), date))
        }

        val size: Int
            get() = data.size

        fun getRow(i: Int) = data[i]
    }


    private val model = Model()

    private class RowHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list_row, parent, false))
    {
        fun bind(row: Row) {
            itemView.long_text.text = row.longText
            itemView.time_text.text = DateFormat.format("dd/MM/yyyy hh:mm:ss", row.time)
        }

    }


    private class ListAdapter(val model: Model) : RecyclerView.Adapter<RowHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
            val inflater = LayoutInflater.from(parent.context)
            return RowHolder(inflater, parent)
        }

        override fun getItemCount(): Int = model.size

        override fun onBindViewHolder(holder: RowHolder, position: Int) {
            holder.bind(model.getRow(position))
        }

    }


    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_page, container, false)
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = ListAdapter(model)
        return view
    }

    override fun onAttach(context: Context) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment FirstPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = FirstPageFragment()
    }
}
