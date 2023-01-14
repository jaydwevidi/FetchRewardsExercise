package com.example.fetchrewardsexercise.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewards.models.NameObject
import com.example.fetchrewardsexercise.api.FetchNameHelper
import com.example.fetchrewardsexercise.api.FetchNameService
import com.example.fetchrewardsexercise.databinding.ActivityMainBinding
import com.example.fetchrewardsexercise.repository.NameRepository
import com.example.fetchrewardsexercise.ui.recyclerview.MainRvAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val myRv= binding.rewardsRecyclerView
        val fetchNameService = FetchNameHelper.getInstance().create(FetchNameService::class.java)
        val repository = NameRepository(fetchNameService)
        val mainViewModel = ViewModelProvider(this , MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.names.observe(this){
            val mainAdapter = MainRvAdapter(dataSet = it)
            myRv.apply {

                adapter = mainAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)

            }
            setupSearchView(it , mainAdapter)
        }
    }

    private fun setupSearchView(completeList : NameObject, adapter : MainRvAdapter){

        val searchView = binding.searchView
        val searchList = NameObject()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                searchList.clear()
                for(i in completeList){
                    if (i.name?.contains(newText.toString()) == true || i.listId.toString().contains(newText.toString())){
                        searchList.add(i)
                    }
                }

                adapter.updateDataset(searchList)
                return true
            }
        })
    }
}