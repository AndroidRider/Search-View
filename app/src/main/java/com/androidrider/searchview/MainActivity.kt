package com.androidrider.searchview


import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.androidrider.searchview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: ArrayAdapter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listView = binding.listView

        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)// this is Important

        adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.countries_array))

        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { adapterView, _, i, _ ->

            Toast.makeText(this@MainActivity, adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show()
        }
        listView.emptyView = binding.emptyView


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_item, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}