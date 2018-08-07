package com.szpejsoft.mydiet.screens.nourishments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.toast
import com.szpejsoft.mydiet.MyDietFragment
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.domain.Nourishment
import kotlinx.android.synthetic.main.nourishment_list_fragment.*

class NourishmentListFragment : MyDietFragment() {

    private val viewModel: NourishmentListViewModel by lazy { viewModelFactory.create(NourishmentListViewModel::class.java) }
    private lateinit var adapter: NourishmentListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.nourishment_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        adapter = NourishmentListAdapter(activity!!)
        adapter.setNavigateToDetailsCallback { nourishment -> showNourishmentEdit(nourishment) }
        nourishmentList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@NourishmentListFragment.context)
            adapter = this@NourishmentListFragment.adapter
        }
    }

    private fun showNourishmentEdit(nourishment: Nourishment) {
        context?.toast("edit nourishment $nourishment")
    }

    private fun observeViewModel() {
        viewModel.nourishment.observe(this, Observer { list -> showList(list) })
    }

    private fun showList(list: List<Nourishment>?) {
        val safeList = list ?: emptyList()

    }
}