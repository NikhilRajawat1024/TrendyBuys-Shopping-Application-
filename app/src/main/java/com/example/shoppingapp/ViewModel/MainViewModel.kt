package com.example.shoppingapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.util.Log
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.example.shoppingapp.Model.BrandModel
import com.example.shoppingapp.Model.ItemsModel

class MainViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<SliderModel>>()

    private val _brand = MutableLiveData<MutableList<BrandModel>>()

    private val _popular = MutableLiveData<MutableList<ItemsModel>>()

    val brands:LiveData<MutableList<BrandModel>> = _brand

    val popular:LiveData<MutableList<ItemsModel>> = _popular

    val banners: LiveData<List<SliderModel>> = _banner

    fun loadBanner() {
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()

                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null) {
                        Log.d("MainViewModel", "Loaded banner URL: ${list.url}")
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Database error: ${error.message}")
            }
        })
    }

    fun loadBrand(){
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for(childSnapshot in snapshot.children){
                    val list  = childSnapshot.getValue(BrandModel::class.java)
                    if(list!=null){
                        Log.d("MainViewModel", "Loaded brand URL: ${list.picUrl}")
                        lists.add(list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Database error: ${error.message}")
            }

        })
    }

    fun loadPopular(){
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for(childSnapshot in snapshot.children){
                    val list  = childSnapshot.getValue(ItemsModel::class.java)
                    if(list!=null){
                        Log.d("MainViewModel", "Loaded brand URL: ${list.picUrl}")
                        lists.add(list)
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainViewModel", "Database error: ${error.message}")
            }

        })
    }
}
