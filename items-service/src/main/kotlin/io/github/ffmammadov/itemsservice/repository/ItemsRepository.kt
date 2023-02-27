package io.github.ffmammadov.itemsservice.repository

import io.github.ffmammadov.itemsservice.repository.entity.ItemEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemsRepository: CrudRepository<ItemEntity, Int>
