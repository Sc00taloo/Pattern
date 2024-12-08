interface USBDevice {
    fun connectWithUSB()
}

class MicroUSBDevice {
    fun connectWithMicroUSB() {
        println("Hello World!")
    }
}

// Адаптер
class MicroUSBToUSBAdapter(private val microUSBDevice: MicroUSBDevice) : USBDevice {
    override fun connectWithUSB() {
        println("Adapter work")
        microUSBDevice.connectWithMicroUSB()
    }
}

fun main() {
    val microUSBDevice = MicroUSBDevice()
    val usbAdapter: USBDevice = MicroUSBToUSBAdapter(microUSBDevice)
    usbAdapter.connectWithUSB()
}