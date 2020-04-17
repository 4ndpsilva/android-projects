package br.com.myfinances.viewmodel

import br.com.myfinances.data.entity.Entry
import br.com.myfinances.repository.EntryRepository

class EntryViewModel(repository: EntryRepository) : BaseViewModel<Entry>(repository)