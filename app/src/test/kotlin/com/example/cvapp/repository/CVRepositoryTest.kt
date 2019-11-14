package com.example.cvapp.repository

import com.example.cvapp.models.CVResponse
import com.example.cvapp.network.CVApi
import com.example.cvapp.util.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CVRepositoryTest {

    //System under test
    private lateinit var cvRepository: CVRepoImpl

    @Mock
    lateinit var cvApi: CVApi

    @BeforeEach
    fun initEach() {
        MockitoAnnotations.initMocks(this)
        cvRepository = CVRepoImpl(cvApi)
    }

    @Test
    fun getCVData_returnSuccess() {

        //Arrange
        var response: CVResponse = CVResponse("abc@gmail.com", "Test User", emptyList(), "000-000-0000", "Dummy Summary")
        var returnedData: Flowable<CVResponse>? = Flowable.just(response)
        whenever(cvApi.getCVData()).thenReturn(returnedData)

        //Act
        var returnedValue: Resource<CVResponse> = cvRepository.getCVData().blockingFirst() as Resource<CVResponse>

        //Assert
        verify(cvApi).getCVData()
        verifyNoMoreInteractions(cvApi)

        println("Returned value: ${returnedValue.data}")

        returnedValue.data?.pastExperience?.isEmpty()?.let { assert(it) }
        assertEquals(Resource.Success(response).data?.email, returnedValue.data?.email)
        assertEquals(Resource.Success(response).data?.name, returnedValue.data?.name)
        assertEquals(Resource.Success(response).data?.pastExperience, returnedValue.data?.pastExperience)
        assertEquals(Resource.Success(response).data?.professionalSummary, returnedValue.data?.professionalSummary)
        assertEquals(Resource.Success(response).data?.phones, returnedValue.data?.phones)
    }

    @Test
    fun getCVData_returnFailure() {

        //Arrange
        var failedResponse: CVResponse = CVResponse("null", "null", null, "null", "null")
        var returnedData: Flowable<CVResponse>? = Flowable.just(failedResponse)
        whenever(cvApi.getCVData()).thenReturn(returnedData)

        //Act
        var returnedValue: Resource<CVResponse> = cvRepository.getCVData().blockingFirst() as Resource<CVResponse>

        //Assert
        verify(cvApi).getCVData()
        verifyNoMoreInteractions(cvApi)

        println("Returned value: ${returnedValue.data}")

        returnedValue.data?.pastExperience?.isNullOrEmpty()?.let { assert(it) }

        assertEquals(Resource.Success(failedResponse).data?.email, returnedValue.data?.email)
        assertEquals(Resource.Success(failedResponse).data?.name, returnedValue.data?.name)
        assertEquals(Resource.Success(failedResponse).data?.pastExperience, returnedValue.data?.pastExperience)
        assertEquals(Resource.Success(failedResponse).data?.professionalSummary, returnedValue.data?.professionalSummary)
        assertEquals(Resource.Success(failedResponse).data?.phones, returnedValue.data?.phones)
    }
}