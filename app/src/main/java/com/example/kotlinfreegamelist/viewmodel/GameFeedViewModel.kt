package com.example.kotlinfreegamelist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.kotlinfreegamelist.model.GameModel
import com.example.kotlinfreegamelist.service.ServiceAPI
import com.example.kotlinfreegamelist.util.GameDbBuilder
import com.example.kotlinfreegamelist.util.SharedPreferencesForTime
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class GameFeedViewModel(application: Application): MainViewModel(application) {

    private val serviceApi = ServiceAPI()
    private val disposable = CompositeDisposable()
    private val sharedPreferences = SharedPreferencesForTime(getApplication())
    private val refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    private val dao = GameDbBuilder(getApplication()).gameDao()

    val games = MutableLiveData<List<GameModel>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()


    // get data from api
    private fun getDataFromAPI() {

        val service = serviceApi.getData()

        loading.value = true

        disposable
            .add(
                service
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object: DisposableSingleObserver<List<GameModel>>() {
                        override fun onSuccess(t: List<GameModel>) {
                            // data -> sqlite
                            storeDataToSQLite(t)
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                            error.value = true
                            loading.value = false
                        }

                    } )
            )


    }

    // get data from sqlite
    private fun getDataFromSQLite() {

        loading.value = true

        launch {
            val getGames = dao.getAllGames()

            // show data
            showData(getGames)
        }
    }

    // show data
    private fun showData(gameList: List<GameModel>) {
        games.value = gameList
        error.value = false
        loading.value = false
    }

    // store data to sqlite
    private fun storeDataToSQLite(gameList: List<GameModel>) {
        launch {
            dao.deleteAllGames()
            val listLong = dao.insertGame(*gameList.toTypedArray())

            var i = 0
            while (i < listLong.size) {
                gameList[i].uuid = listLong[i].toInt()
                i++
            }

            showData(gameList)
        }

        sharedPreferences.saveTime(System.nanoTime())
    }


    // refresh data from api
    fun refreshDataFromAPI() {
        getDataFromAPI()
    }

    // refresh data
    fun refreshData() {
        val time = sharedPreferences.getTime()

        if (time != null && time != 0L && System.nanoTime() - time < refreshTime) {
            // get data from sqlite
            getDataFromSQLite()
        } else {
            // get data from api
            getDataFromAPI()
        }
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}