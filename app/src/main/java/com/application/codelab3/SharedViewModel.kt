package com.application.codelab3

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
  private val _tasks = setData().toMutableStateList()
    val tasks = _tasks

    fun removeTask(item:TasksData){
        tasks.remove(item)
    }

}
fun setData() = List(30) {i-> TasksData(i,"Task # $i")}
data class TasksData(val id:Int,val label:String)