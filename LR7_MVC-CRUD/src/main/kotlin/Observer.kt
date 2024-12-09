interface Observer {
    fun update(value: Int)
}

class Counter {
    private val observers = mutableListOf<Observer>()
    var value: Int = 0
        private set

    // Регистрация наблюдателя
    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    // Увеличение значения
    fun increment() {
        value++
        notifyObservers()
    }

    // Уведомление
    private fun notifyObservers() {
        for (observer in observers) {
            observer.update(value)
        }
    }
}

class ConsoleObserver : Observer {
    override fun update(value: Int) {
        println("Значение изменилось на $value")
    }
}

fun main() {
    val counter = Counter()
    val consoleObserver = ConsoleObserver()

    counter.addObserver(consoleObserver)

    counter.increment()
    counter.increment()
    counter.increment()
}