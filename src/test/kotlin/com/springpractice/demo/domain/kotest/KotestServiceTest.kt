package com.springpractice.demo.domain.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class KotestServiceTest : StringSpec({

    "createMessage:: when user name is exist then return message" {
        val kotestRepository = mockk<KotestRepository>()
        val target = KotestService(kotestRepository)

        val id = 100

        every { kotestRepository.findName(any()) } returns "Kotest"

        val result = target.createMessage(id)

        result shouldBe "Hello Kotest"
        verify { kotestRepository.findName(id) }
    }

    "createMessage:: when user name is not exist then return null" {
        val kotestRepository = mockk<KotestRepository>()
        val target = KotestService(kotestRepository)

        val id = 100

        every { kotestRepository.findName(any()) } returns null

        val result = target.createMessage(id)

        result shouldBe null
        verify { kotestRepository.findName(id) }
    }

    "createMessage:: when id is less than 0 then return null" {
        val kotestRepository = mockk<KotestRepository>()
        val target = KotestService(kotestRepository)

        val id = -1

        every { kotestRepository.findName(any()) } returns null

        val result = target.createMessage(id)

        result shouldBe null
        verify(exactly = 0) { kotestRepository.findName(any()) }
    }
})
