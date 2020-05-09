package com.example.miniapp.controller

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.R
import com.example.miniapp.model.Api

class ListActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ListAdapter
    private lateinit var mNoIndicatorsView: TextView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var indicator: Api
    var indicatorsById: MutableMap<String, Api> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        mNoIndicatorsView = findViewById(R.id.no_indicators_view) as TextView
        mRecyclerView = findViewById(R.id.my_recycler_view) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = mLayoutManager
        mAdapter = ListAdapter(emptyList())
        mRecyclerView.adapter = (mAdapter)
        mAdapter.setOnClickListener { codigo ->

            startActivity(MainActivity.createIntent(this, codigo))
                    indicator = indicatorsById[codigo]!!

        }
    }
    override fun onStart() {
        super.onStart()
        val indicators = indicatorsById.values.toList()
        if (indicators.isEmpty()) {
            mNoIndicatorsView.visibility = View.VISIBLE
            mRecyclerView.visibility = View.GONE
        } else {
            mAdapter.setIndicators(indicatorsById.values.toList())
        }
    }
}