import com.example.estdelivery.application.domain.model.CouponConfig
import com.example.estdelivery.application.domain.model.CouponType

object CouponConfigTestFixture {
    fun couponConfig(): CouponConfig = CouponConfig(1, CouponType.AMOUNT, "")
}