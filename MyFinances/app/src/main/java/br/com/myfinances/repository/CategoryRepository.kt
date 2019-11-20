package br.com.myfinances.repository

import br.com.myfinances.data.dao.CategoryDAO
import br.com.myfinances.data.entity.Category

class CategoryRepository(var dao: CategoryDAO) : BaseRepository<Category>(dao)