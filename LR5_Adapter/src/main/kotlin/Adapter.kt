// Старая версия API
class OldPrinter {
    fun printText(text: String) {
        println("Printing: $text")
    }
}

// Новый интерфейс
interface NewPrinterInterface {
    fun printMessage(message: String)
}

// Адаптер
class PrinterAdapter(private val oldPrinter: OldPrinter) : NewPrinterInterface {
    override fun printMessage(message: String) {
        oldPrinter.printText(message)
    }
}

// Клиентский код
fun main() {
    val oldPrinter = OldPrinter()
    val adapter = PrinterAdapter(oldPrinter)

    // Используем новый интерфейс
    adapter.printMessage("Hello, Adapter Pattern!")
}