package com.mason.hoodie

import com.mason.hoodie.data.MavenRepository
import com.mason.hoodie.ioc.repositoryModule
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest

class RepositoryUnitTest : KoinTest {
    private val repo: MavenRepository by inject()

    @Test
    fun mavenTest() {
        startKoin(listOf(repositoryModule))
        runBlocking {
            System.out.println("Request Groups")
            try {
                val groups = repo.getGroups("io.reactivex.rxjava2").await()
                System.out.println(groups)
                System.out.println("Request Groups Success")
            } catch (e: Exception) {
                System.out.println("Request Groups Fail")
                e.printStackTrace()
            }

            System.out.println("Request Artifacts With Group")
            try {
                val artifactsWithGroup = repo.getArtifactsWithGroup("io.reactivex.rxjava2", "rxjava").await()
                System.out.println(artifactsWithGroup)
                System.out.println("Request Artifacts With Group Success")
            } catch (e: Exception) {
                System.out.println("Request Artifacts With Group Fail")
                e.printStackTrace()
            }

            System.out.println("Request Artifacts And Group")
            try {
                val artifactsWithGroup = repo.getArtifactsAndGroup("rxjava").await()
                System.out.println(artifactsWithGroup)
                System.out.println("Request Artifacts And Group Success")
            } catch (e: Exception) {
                System.out.println("Request Artifacts And Group Fail")
                e.printStackTrace()
            }

            System.out.println("Request Artifacts")
            try {
                val artifacts = repo.getArtifacts("rxjava").await()
                System.out.println(artifacts)
                System.out.println("Request Artifacts Success")
            } catch (e: Exception) {
                System.out.println("Request Artifacts Fail")
                e.printStackTrace()
            }
        }
    }
}