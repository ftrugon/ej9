import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Tarea(val descripcion: String, var estado: Estado = Estado.PENDIENTE) {
    val id: Int = contadorIds++
    var fechaRealizacion: LocalDateTime? = null

    enum class Estado {
        PENDIENTE, REALIZADA
    }

    fun marcarRealizada() {
        if (estado == Estado.PENDIENTE) {
            estado = Estado.REALIZADA
            fechaRealizacion = LocalDateTime.now()
        }
    }

    override fun toString(): String {
        return "ID: $id - Descripción: $descripcion - Estado: $estado - Fecha: ${formatoFechaRealizacion()}"
    }

    private fun formatoFechaRealizacion(): String {
        return fechaRealizacion?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) ?: "N/A"
    }

    companion object {
        private var contadorIds = 1
    }
}