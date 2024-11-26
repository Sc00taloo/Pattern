interface DeliveryStrategy {
    fun deliveryCost(distance: Double): Double
}

class StandardDelivery : DeliveryStrategy {
    override fun deliveryCost(distance: Double): Double {
        return distance * 1.0
    }
}

class ExpressDelivery : DeliveryStrategy {
    override fun deliveryCost(distance: Double): Double {
        return distance * 2.0
    }
}

class FreeDelivery : DeliveryStrategy {
    override fun deliveryCost(distance: Double): Double {
        return 0.0
    }
}

class DeliveryService(private var strategy: DeliveryStrategy) {
    fun setStrategy(strategy: DeliveryStrategy) {
        this.strategy = strategy
    }
    fun cost(distance: Double): Double {
        return strategy.deliveryCost(distance)
    }
}

fun main() {
    val deliveryService = DeliveryService(StandardDelivery())
    val distance = 10.0

    println("Стандартная доставка: ${deliveryService.cost(distance)}")

    deliveryService.setStrategy(ExpressDelivery())
    println("Экспресс-доставка: ${deliveryService.cost(distance)}")

    deliveryService.setStrategy(FreeDelivery())
    println("Бесплатная доставка: ${deliveryService.cost(distance)}")
}