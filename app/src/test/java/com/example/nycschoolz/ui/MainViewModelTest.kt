package com.example.nycschoolz.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschoolz.api.ApiInterface
import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import com.example.nycschoolz.repo.FakeRepo
import com.example.nycschoolz.repo.RepoInterface
import com.example.nycschoolz.repo.RepoLocal
import com.example.nycschoolz.repo.RepoRemote
import com.example.nycschoolz.room.SchoolDAO
import com.example.nycschoolz.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class MainViewModelTest {

    val dispatcher = TestCoroutineDispatcher()
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFake: MainViewModel

    lateinit var remote: RepoRemote
    lateinit var local: RepoLocal
    lateinit var fakeRepo: FakeRepo

    @Mock
    lateinit var apiInterface: ApiInterface
    @Mock
    lateinit var schoolDAO: SchoolDAO



    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        remote = RepoRemote(apiInterface)
        local = RepoLocal(schoolDAO)
        fakeRepo = FakeRepo(schoolDAO)
        viewModel = MainViewModel(schoolDAO, local, remote)
        viewModelFake = MainViewModel(schoolDAO, fakeRepo, remote)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `api call is successful and response is empty list of school models`() = runBlocking {
        val expected = listOf<SchoolModelItemModel>()
        whenever(remote.getSchools()).thenReturn(expected)

        val actual = viewModel.schools

        assertEquals(expected, actual)
    }

    @Test
    fun `api call is successful and response is empty list of sat model`() = runBlocking {
        val expected = listOf<SATsModelItem>()
        whenever(remote.getSAT("NYC")).thenReturn(expected)

        val actual = viewModel.sat

        assertEquals(expected, actual)
    }

    @Test
    fun `insert data into db`(){
        viewModelFake.insertIntoDatabase(model = ArrayList<SchoolModelItemModel>())
        assertEquals(fakeRepo.list, listOf<SchoolModelItemModel>())
    }

    @Test
    fun `read data from database`(){
        fakeRepo.list.add(SchoolModelItemModel(schoolName = "NYC"))
        viewModelFake.getDataFromDB()
        assertEquals(fakeRepo.list, listOf(SchoolModelItemModel(schoolName = "NYC")))

    }



}