package br.com.myfinances.viewmodel

import br.com.myfinances.data.entity.Account
import br.com.myfinances.repository.AccountRepository

class AccountViewModel(repository: AccountRepository) : BaseViewModel<Account>(repository)