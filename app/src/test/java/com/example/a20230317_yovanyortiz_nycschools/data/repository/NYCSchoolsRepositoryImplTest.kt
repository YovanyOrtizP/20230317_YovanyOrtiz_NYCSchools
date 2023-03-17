package com.example.a20230317_yovanyortiz_nycschools.data.repository

import com.example.a20230317_yovanyortiz_nycschools.data.remote.NYCApi
import com.example.a20230317_yovanyortiz_nycschools.util.ResponseType
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NYCSchoolsRepositoryImplTest {
//Test object (repo), NYCApi,Test Dispatcher, Test Scope

    //Todo lo que se necesite inicializar antes del test:
    //Dispatcher u object

    private lateinit var testObject: NYCSchoolsRepository

    private val mockApi = mockk<NYCApi>(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val  testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        testObject = NYCSchoolsRepositoryImpl(mockApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    //Testing Schools Info

    @Test
    fun `get schools info when the server retrieves a list of schools returns a SUCCESS state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSchoolsInfo() } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(mockk(), mockk(), mockk())
        }

        // ACTION
        val job = testScope.launch {
            testObject.getSchoolsInfoFlow().collect {
                if (it is ResponseType.SUCCESS) {

                    //ASSERTION
                    assert(it is ResponseType.SUCCESS)
                    assertEquals(3, it.response.size)
                }
            }
        }

        job.cancel()
    }

    @Test
    fun `get schools info when the server retrieves a null list of schools returns a ERROR state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSchoolsInfo() } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns null
        }

        // ACTION
        val job = testScope.launch {
            testObject.getSchoolsInfoFlow().collect {
                if (it is ResponseType.ERROR) {

                    //ASSERTION
                    assert(it is ResponseType.ERROR)
                    assertEquals("Info not available", it.e)
                }
            }
        }

        job.cancel()
    }

    @Test
    fun `get schools info when the server retrieves a failure response returns a ERROR state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSchoolsInfo() } returns mockk {
            every { isSuccessful } returns false
            every { errorBody() } returns mockk {
                every { string() }  returns "ERROR"
            }
        }

        // ACTION
        val job = testScope.launch {
            testObject.getSchoolsInfoFlow().collect {
                if (it is ResponseType.ERROR) {

                    //ASSERTION
                    assertEquals("ERROR", it.e)
                }
            }
        }

        job.cancel()
    }

    @Test
    fun `get schools info when the server THROWS AN EXCEPTION returns a ERROR state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSchoolsInfo() } throws Exception("ERROR")

        // ACTION
        val job = testScope.launch {
            testObject.getSchoolsInfoFlow().collect {
                if (it is ResponseType.ERROR) {

                    //ASSERTION
                    assertEquals("ERROR", it.e)
                }
            }
        }

        job.cancel()
    }

    //Testing SAT Info

    @Test
    fun `get sat by DBN when the server retrieves a list of SAT scores returns a SUCCESS state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSatInfo("A234D") } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(mockk(), mockk(), mockk())
        }

        // ACTION
        val job = testScope.launch {
            testObject.getSatInfoFlow("A234D").collect {
                if (it is ResponseType.SUCCESS) {

                    //ASSERTION
                    assert(it is ResponseType.SUCCESS)
                    assertEquals(3, it.response.size)
                }
            }
        }

        job.cancel()
    }

    @Test
    fun `get sat info when the server retrieves an empty list of SAT returns a ERROR state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSatInfo("A234D") } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns null
        }

        // ACTION
        val job = testScope.launch {
            testObject.getSatInfoFlow("A234D").collect {
                if (it is ResponseType.ERROR) {

                    //ASSERTION
                    assert(it is ResponseType.ERROR)
                    assertEquals("Info not available", it.e)
                }
            }
        }

        job.cancel()
    }

    @Test
    fun `get SAT info when the server retrieves a failure response returns a ERROR state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSatInfo("A234D") } returns mockk {
            every { isSuccessful } returns false
            every { errorBody() } returns mockk {
                every { string() }  returns "ERROR"
            }
        }

        // ACTION
        val job = testScope.launch {
            testObject.getSatInfoFlow("A234D").collect {
                if (it is ResponseType.ERROR) {

                    //ASSERTION
                    assertEquals("ERROR", it.e)
                }
            }
        }

        job.cancel()
    }

    @Test
    fun `get SAT info when the server THROWS AN EXCEPTION returns a ERROR state`() {
        // AAA

        // assignment
        coEvery { mockApi.getSatInfo("A234D") } throws Exception("ERROR")

        // ACTION
        val job = testScope.launch {
            testObject.getSatInfoFlow("A234D").collect {
                if (it is ResponseType.ERROR) {

                    //ASSERTION
                    assertEquals("ERROR", it.e)
                }
            }
        }

        job.cancel()
    }
}