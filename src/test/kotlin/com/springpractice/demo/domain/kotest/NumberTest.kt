package com.springpractice.demo.domain.kotest

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class NumberTestByStringSpec : StringSpec() {
    init {
        "isOdd:: when value is odd number then return true" {
            val number = Number(1)
            number.isOdd() shouldBe true
            // number.isOdd().shouldBe(true)
        }

        "isOdd:: when value is even number then return false" {
            val number = Number(2)
            number.isOdd() shouldBe false
        }

        "isRange:: when value in range then return true" {
            forAll(
                table(
                    headers("value"),
                    row(1),
                    row(10)
                )
            ) {
                val number = Number(it)
                number.isRange(1, 10) shouldBe true
            }
        }

        "isRange:: when value not in range then return false" {
            forAll(
                table(
                    headers("value"),
                    row(0),
                    row(11)
                )
            ) { value ->
                val number = Number(value)
                number.isRange(1, 10) shouldBe false
            }
        }
    }
}

class NumberTestByBehaviorSpec : BehaviorSpec() {
    init {
        given("isOdd") {
            `when`("num is odd number") {
                val number = Number(1)
                then("return true") {
                    number.isOdd() shouldBe true
                }
            }

            `when`("num is even number") {
                val number = Number(2)
                then("return false") {
                    number.isOdd() shouldBe false
                }
            }
        }
    }
}

class NumberTestAnnotationSpec : AnnotationSpec() {
    @Test
    fun `isOdd when value is odd number then return true`() {
        val number = Number(1)
        number.isOdd().shouldBe(true)
    }

    @Test
    fun `isOdd when value is even number then return false`() {
        val number = Number(2)
        number.isOdd().shouldBe(false)
    }
}
