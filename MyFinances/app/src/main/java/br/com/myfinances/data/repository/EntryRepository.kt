package br.com.myfinances.data.repository

import br.com.myfinances.data.dao.EntryDAO
import br.com.myfinances.data.entity.Entry

class EntryRepository(private val dao: EntryDAO) : BaseRepository<Entry>(dao)