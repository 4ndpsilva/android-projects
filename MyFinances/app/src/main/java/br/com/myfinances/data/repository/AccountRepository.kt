package br.com.myfinances.data.repository

import br.com.myfinances.data.dao.AccountDAO
import br.com.myfinances.data.entity.Account

class AccountRepository(private val dao: AccountDAO) : BaseRepository<Account>(dao)