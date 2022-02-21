package com.example.myshop.data.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.myshop.data.ShopDataBase
import com.example.myshop.data.ShopDatabaseDao
import com.example.myshop.domain.model.Item
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ShopDatabaseDaoTest {

    private lateinit var shopDao: ShopDatabaseDao
    private lateinit var database: ShopDataBase

    // helpers
    private val testItem = Item(11, "Bread", 25.00, 35.00, userId = 20)
    private val testItem2 = Item(12, "Soap", 25.00, 35.00, userId = 24)
    private val testItem3 = Item(13, "Rina oil", 25.00, 35.00, userId = 4)
    private val testItems = listOf(testItem, testItem2)
    private val testItems2 = listOf(testItem, testItem2, testItem3)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShopDataBase::class.java
        ).allowMainThreadQueries().build()
        shopDao = database.shopDBDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `delete item removes the item from database`() = runBlocking {
        shopDao.addItem(testItem)
        assertThat(shopDao.getItems()).isNotEmpty()
        shopDao.deleteItem(testItem)
        assertThat(shopDao.getItems()).isEmpty()
    }

    @Test
    fun `shopdao, get items returns a list of items`() = runBlocking {
        shopDao.addItem(testItem)
        assertThat(shopDao.getItems()).isEqualTo(testItems)
    }

    @Test
    fun `saveItems saves a list of items`() = runBlocking {
        shopDao.saveItems(testItems2)
        assertThat(shopDao.getItems().size).isEqualTo(3)
    }

    @Test
    fun `searchItemsByName returns a list of items matching the given name`() = runBlocking {
        
    }
}
