package br.com.myfinances.repository

import br.com.myfinances.rest.MobileApi
import br.com.myfinances.rest.SafeApiRequest

class CategoryRepository(private val api: MobileApi) : SafeApiRequest(){
    suspend fun getAll() = apiRequest { api.getAll() }
}