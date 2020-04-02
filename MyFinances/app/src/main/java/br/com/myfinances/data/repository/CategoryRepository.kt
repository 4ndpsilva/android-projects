package br.com.myfinances.data.repository

import br.com.myfinances.MobileApi
import br.com.myfinances.SafeApiRequest

class CategoryRepository(var api: MobileApi) : SafeApiRequest(){
    suspend fun getAll() = apiRequest { api.getAll() }
}