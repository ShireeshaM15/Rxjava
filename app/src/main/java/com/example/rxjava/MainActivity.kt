package com.example.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
       // val binding:ActivityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)


        binding.recyclerview.adapter = adapter


        binding.fetchproducts.setOnClickListener {
            viewModel.getMaybelProducts()
            binding.fetchproducts.visibility = View.GONE
            binding.recyclerview.visibility = View.VISIBLE

        }
        viewModel.maybelineLIst.observe(this, Observer {
            if (it != null) {
                Log.d("list", "maybelline: $it")
                adapter.productlist(it)
            } else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d("error", "errorMessage: $it")
        })
    }

    override fun onDestroy() {
        //don't send events  once the activity is destroyed
        viewModel.disposable.dispose()
        super.onDestroy()
    }
}