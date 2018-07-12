package com.mason.repeater.ui.repeatlist

import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.mason.repeater.BaseFragment
import com.mason.repeater.R
import kotlinx.android.synthetic.main.fragment_repeat_list.*

class RepeatListFragment : BaseFragment() {
    lateinit var adapter: RepeatListAdapter
    lateinit var listener: RepeatListAdapter.OnClickRepeatDataListener

    companion object {
        fun newInstance(): RepeatListFragment = RepeatListFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is RepeatListAdapter.OnClickRepeatDataListener) {
            this.listener = context
        } else {
            throw Exception("$context must implement OnClickRepeatDataListener")
        }
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_repeat_list

    override fun initView() {
        adapter = RepeatListAdapter(listener)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        RepeatListViewModel.INSTANCE.repeatList.observe(this, Observer { it?.let { adapter.setList(it) } })
    }
}