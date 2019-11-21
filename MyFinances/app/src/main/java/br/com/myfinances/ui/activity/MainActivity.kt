package br.com.myfinances.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.myfinances.viewmodel.AccountViewModel
import br.com.myfinances.viewmodel.CategoryViewModel
import br.com.myfinances.viewmodel.Creator

class MainActivity : AppCompatActivity(){
    private val categoryVM: CategoryViewModel by viewModels { Creator.viewModelCategory(this) }
    private val accountVM: AccountViewModel by viewModels { Creator.viewModelAccount(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryVM.list.observeForever {  }
        accountVM.list.observeForever {  }
    }
}