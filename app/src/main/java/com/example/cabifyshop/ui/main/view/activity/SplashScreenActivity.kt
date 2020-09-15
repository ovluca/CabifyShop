package com.example.cabifyshop.ui.main.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.cabifyshop.R
import com.example.cabifyshop.data.api.FetchProductsWorker
import com.example.cabifyshop.ui.main.viewmodel.SplashScreenViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SplashScreenActivity : AppCompatActivity() {

    lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)

        setupWorker()
    }

    /**
     * FetchProductsWorker downloads products and populate local database,
     * The worker is started only if NetworkType.CONNECTED, if is not, a check for products is made in the local database,
     * if local database have products the app can be used in offline mode.
     * */

    private fun setupWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val fetchProductsRequest = OneTimeWorkRequestBuilder<FetchProductsWorker>()
            .setConstraints(constraints)
            .build()


        if (viewModel.isNetworkConnected(this)) {
            WorkManager.getInstance(this).enqueue(fetchProductsRequest)
        } else {
            viewModel.databaseHasProducts { hasProducts ->
                run {
                    if (hasProducts) {
                        ShopActivity.open(this)
                        finish()
                    } else {
                        MaterialAlertDialogBuilder(this).apply {
                            setTitle(getString(R.string.connection_error))
                            setMessage(getString(R.string.connection_error_message))
                            setPositiveButton(getString(R.string.ok)) { _, _ -> finishAffinity() }.show()

                        }
                    }
                }
            }
        }



        WorkManager.getInstance(this)
            // requestId is the WorkRequest id
            .getWorkInfoByIdLiveData(fetchProductsRequest.id)
            .observe(this, Observer { workInfo: WorkInfo? ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    ShopActivity.open(this)
                    finish()
                }
            })
    }

}