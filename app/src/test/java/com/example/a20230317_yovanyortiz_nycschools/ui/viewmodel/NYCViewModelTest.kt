package com.example.a20230317_yovanyortiz_nycschools.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20230317_yovanyortiz_nycschools.data.repository.NYCSchoolsRepository
import com.example.a20230317_yovanyortiz_nycschools.util.ResponseType
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class NYCViewModelTest {

    @get:Rule val rule = InstantTaskExecutorRule()

    private lateinit var testObject: NYCViewModel

    private val mockRepository = mockk<NYCSchoolsRepository>(relaxed = true)
    private val mockDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(mockDispatcher)
        testObject = NYCViewModel(mockRepository, mockDispatcher)
    }

    @Before
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `get schools when repository retrieves a list of schools returns SUCCESS state`() {
        every { mockRepository.getSchoolsInfoFlow() } returns flowOf(
            ResponseType.SUCCESS(listOf(mockk(), mockk(), mockk()))
        )
        testObject.resultSchools.observeForever {
            if (it is ResponseType.SUCCESS) {
                assertEquals(3,  it.response.size)
            }
        }

        testObject.getSchoolsInfoFlow()
    }

    @Test
    fun `get schools when repository retrieves a null list of schools returns a ERROR state`() {
        every { mockRepository.getSchoolsInfoFlow() } returns flowOf(
            ResponseType.ERROR("No information founded")
        )
        testObject.resultSchools.observeForever {
            if (it is ResponseType.ERROR) {
                assertEquals("Info not available", it.e)
            }
        }

        testObject.getSchoolsInfoFlow()
    }

    @Test
    fun `get sat when repository retrieves a list of sat returns SUCCESS state`() {
        every { mockRepository.getSatInfoFlow("AS2342") } returns flowOf(
            ResponseType.SUCCESS(listOf(mockk(), mockk(), mockk()))
        )
        testObject.resultSchools.observeForever {
            if (it is ResponseType.SUCCESS) {
                assertEquals(3,  it.response.size)
            }
        }

        testObject.getSchoolsInfoFlow()
    }

    @Test
    fun `get sat when repository retrieves a null list of sat returns a ERROR state`() {
        every { mockRepository.getSatInfoFlow("AS2342") } returns flowOf(
            ResponseType.ERROR("No information founded")
        )
        testObject.resultSchools.observeForever {
            if (it is ResponseType.ERROR) {
                assertEquals("Info not available", it.e)
            }
        }

        testObject.getSchoolsInfoFlow()
    }
}