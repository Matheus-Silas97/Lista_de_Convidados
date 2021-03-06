package com.matheussilas.listadeconvidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussilas.listadeconvidados.R
import com.matheussilas.listadeconvidados.service.GuestConstants
import com.matheussilas.listadeconvidados.view.adapter.GuestAdapter
import com.matheussilas.listadeconvidados.view.listener.GuestListener
import com.matheussilas.listadeconvidados.viewmodel.GuestsViewModel
import kotlinx.android.synthetic.main.fragment_absents.view.*
import kotlinx.android.synthetic.main.fragment_all.view.*

class AbsentsFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_absents, container, false)

        val recycler = root.recycler_absents
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent =  Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.ABSENT)
            }

        }

        mAdapter.attachListener(mListener)

        observer()
        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.ABSENT)
    }
    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}
