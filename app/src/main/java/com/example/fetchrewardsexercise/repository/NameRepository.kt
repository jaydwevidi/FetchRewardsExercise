package com.example.fetchrewardsexercise.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fetchrewards.models.NameObject
import com.example.fetchrewards.models.NameObjectItem
import com.example.fetchrewardsexercise.api.FetchNameService

class NameRepository(val fetchNameService: FetchNameService , val app_context : Application)  {

    val nameLiveData = MutableLiveData<NameObject>()

    fun getSortedNames(list : NameObject) : NameObject  {
        val sortedList = NameObject()

        for (i in list){
            if (i.name != null && i.name != "") {
                sortedList.add(i)
            }
        }
        sortedList.sortWith(compareBy<NameObjectItem>{it.listId}.thenBy { it.name })
        return sortedList
    }

    suspend fun getNames()  {
        val result = fetchNameService.getNames()

        if(result.body() != null){
            val list = result.body() as NameObject
            val sortedList = getSortedNames(list)
            nameLiveData.postValue(sortedList)
        }
        else{
            Log.e("repositoryAPI", "Result body is null . getNames: ${result.body()}")
        }
    }
}