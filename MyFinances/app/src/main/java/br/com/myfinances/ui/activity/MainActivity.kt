package br.com.myfinances.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.myfinances.viewmodel.AccountViewModel
import br.com.myfinances.viewmodel.CategoryViewModel
import br.com.myfinances.viewmodel.Creator

class MainActivity : AppCompatActivity(){
    private val categoryVM: CategoryViewModel by viewModels { Creator.vmFactoryCategory(this) }
    private val accountVM: AccountViewModel by viewModels { Creator.vmFactoryAccount(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryVM.loadList().observeForever {  }
        accountVM.loadList().observeForever {  }
    }
}