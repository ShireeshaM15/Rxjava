package com.example.rxjava

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val maybelineLIst = MutableLiveData<List<ProductModel>>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable






    fun getMaybelProducts() {
        //rxjava
        val response = repository.getMaybelProducts()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMoviesListObserver())
    }

    private fun getMoviesListObserver(): Observer<ProductModel> {
        return object : Observer<ProductModel> {
            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                maybelineLIst.postValue(null)
            }

            override fun onNext(t: ProductModel) {
                maybelineLIst.postValue(listOf(t))
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }
        }
    }
}

