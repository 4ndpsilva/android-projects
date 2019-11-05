package br.com.myfinances.data.dao

import androidx.room.Dao
import br.com.myfinances.data.entity.Account

@Dao
abstract class AccountDAO : GenericDAO<Account>("TB_ACCOUNT")