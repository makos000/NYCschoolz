package com.example.nycschoolz.repo

import com.example.nycschoolz.api.ApiInterface
import com.example.nycschoolz.models.SATsModelItem
import com.example.nycschoolz.models.SchoolModelItemModel
import com.example.nycschoolz.room.SchoolDAO
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class RepoRemoteTest{
    lateinit var remote: RepoRemote

    @Mock
    lateinit var apiInterface: ApiInterface

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        remote = RepoRemote(apiInterface)
    }

    @Test
    fun `get empty list of school model`() = runBlocking{
        val expected = listOf<SchoolModelItemModel>()
        whenever(apiInterface.getSchools()).thenReturn(expected)
        val actual = remote.getSchools()
        assertEquals(expected,actual)
    }

    @Test
    fun `get empty list of sat model`() = runBlocking{
        val expected = listOf<SATsModelItem>()
        whenever(apiInterface.getSAT("NYC school")).thenReturn(expected)
        val actual = remote.getSAT("NYC school")
        assertEquals(expected,actual)
    }
}