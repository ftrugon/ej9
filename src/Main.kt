import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Tarea(val descripcion: String, var estado: Estado = Estado.PENDIENTE) {
    var id: Int = 0
    var fechaRealizacion: LocalDateTime? = null

    enum class Estado {
        PENDIENTE, REALIZADA
    }

    private var contadorIds = 1

    init {
        id = obtenerNuevoId()
    }

    private fun obtenerNuevoId(): Int {
        return contadorIds++
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
}

class ListaTareas {
    private val tareas = mutableListOf<Tarea>()

    fun agregarTarea(descripcion: String) {
        val nuevaTarea = Tarea(descripcion)
        tareas.add(nuevaTarea)
        println("Tarea agregada: $nuevaTarea")
    }

    fun eliminarTarea(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            tareas.remove(tarea)
            println("Tarea eliminada: $tarea")
        } else {
            println("No se encontró una tarea con el ID $id")
        }
    }

    fun cambiarEstadoTarea(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            tarea.marcarRealizada()
            println("Estado cambiado de la tarea: $tarea")
        } else {
            println("No se encontró una tarea con el ID $id")
        }
    }

    fun mostrarTodas() {
        println("Lista de todas las tareas:")
        tareas.forEach { println(it) }
    }

    fun mostrarPendientes() {
        val pendientes = tareas.filter { it.estado == Tarea.Estado.PENDIENTE }
        println("Lista de tareas pendientes:")
        pendientes.forEach { println(it) }
    }

    fun mostrarRealizadas() {
        val realizadas = tareas.filter { it.estado == Tarea.Estado.REALIZADA }
        println("Lista de tareas realizadas:")
        realizadas.forEach { println(it) }
    }
}

fun main() {
    val listaTareas = ListaTareas()

    listaTareas.agregarTarea("Hacer la compra")
    listaTareas.agregarTarea("Estudiar para el examen")

    listaTareas.cambiarEstadoTarea(1)
    listaTareas.mostrarTodas()

    listaTareas.eliminarTarea(2)
    listaTareas.mostrarPendientes()

    listaTareas.agregarTarea("Hacer ejercicio")
    listaTareas.mostrarRealizadas()
}