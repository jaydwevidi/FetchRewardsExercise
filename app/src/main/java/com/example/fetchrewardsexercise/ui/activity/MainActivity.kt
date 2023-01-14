package com.example.fetchrewardsexercise.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewards.models.NameObject
import com.example.fetchrewardsexercise.R
import com.example.fetchrewardsexercise.api.FetchNameHelper
import com.example.fetchrewardsexercise.api.FetchNameService
import com.example.fetchrewardsexercise.databinding.ActivityMainBinding
import com.example.fetchrewardsexercise.repository.NameRepository
import com.example.fetchrewardsexercise.ui.recyclerview.MainRvAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    lateinit var completeList: NameObject
    lateinit var mainRvAdapter : MainRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu , menu)
        val menuItem = menu?.findItem(R.id.action_search_bar)
        val searchView = menuItem?.actionView as SearchView
        val searchList = NameObject()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                for(i in completeList){
                    if (i.name?.contains(newText.toString() , true) == true || i.listId.toString().contains(newText.toString())){
                        searchList.add(i)
                    }
                }
                mainRvAdapter.updateDataset(searchList)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupRecyclerView(){
        val myRv= binding.rewardsRecyclerView
        val fetchNameService = FetchNameHelper.getInstance().create(FetchNameService::class.java)
        val repository = NameRepository(fetchNameService)
        val mainViewModel = ViewModelProvider(this , MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.names.observe(this){
            mainRvAdapter = MainRvAdapter(dataSet = it)
            completeList = it
            myRv.apply {
                adapter = mainRvAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }
}