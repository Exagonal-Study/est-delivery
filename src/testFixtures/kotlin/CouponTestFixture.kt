import com.example.estdelivery.application.domain.model.Coupon

object CouponTestFixture {
    fun coupon(): Coupon = Coupon.create(1)

    fun coupons(): List<Coupon> = listOf(Coupon.create(1), Coupon.create(2))
}