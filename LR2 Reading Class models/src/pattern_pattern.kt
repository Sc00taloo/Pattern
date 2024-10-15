// Шаблон - это паттерн проектирования, который позволяет определить скелет
// алгоритма в базовом классе, но реализует конкретные шаги в дочерних классах.
// То есть, данный паттерн задаёт структуру алгоритма, но позволяет подклассам переопределять
// определенные шаги, не меняя при этом структуру алгоритма в целом.

abstract class CoffeeTemplate {
    // Шаблонный метод
    fun prepareRecipe() {
        boilWater()
        brewCoffeeGrinds()
        pourInCup()
        addCondiments()
    }

    // Шаги, которые не изменяются
    private fun boilWater() {
        println("Кипятим воду")
    }

    private fun pourInCup() {
        println("Наливаем кипяток в чашку")
    }

    // Шаги, которые будут переопределены в подклассах
    abstract fun brewCoffeeGrinds()
    abstract fun addCondiments()
}

class Tea : CoffeeTemplate() {
    override fun brewCoffeeGrinds() {
        println("Завариваем чайные листья")
    }

    override fun addCondiments() {
        println("Добавляем лимон")
    }
}

class Coffee : CoffeeTemplate() {
    override fun brewCoffeeGrinds() {
        println("Завариваем кофейные зерна")
    }

    override fun addCondiments() {
        println("Добавляем сахар и молоко")
    }
}

fun main() {
    val tea = Tea()
    tea.prepareRecipe()
    println()
    val coffee = Coffee()
    coffee.prepareRecipe()
}