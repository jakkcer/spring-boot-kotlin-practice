package com.springpractice.demo.domain.rental

import com.springpractice.demo.infrastructure.database.mapper.RentalMapper
import com.springpractice.demo.infrastructure.database.mapper.insert
import com.springpractice.demo.infrastructure.database.record.RentalRecord
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class RentalRepositoryImpl(
    private val rentalMapper: RentalMapper
) : RentalRepository {
    override fun startRental(rental: Rental) {
        rentalMapper.insert(toRecord(rental))
    }

    private fun toRecord(model: Rental): RentalRecord {
        return RentalRecord(model.bookId, model.userId, model.rentalDatetime, model.returnDeadline)
    }
}
