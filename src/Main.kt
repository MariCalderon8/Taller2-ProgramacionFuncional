
val users = mutableListOf<User>()

fun main() {
    printMenu()

    while (true){
        var userResponse = readLine()
        when (userResponse){
            "1" ->{
                println("--------------- Buscar usuario ---------------")
                println("Ingresa el id del usuario:")
                val userId = readLine()!!.toInt()
                val user = findUser(userId)
                if (user != null) {
                    with(user) {
                        println("""
                            ID: $id
                            Nombre: $name
                            Correo: $email
                        """.trimIndent())
                    }
                } else {
                    println("No pudimos encontrar el usuario que estabas buscando.")
                }
            }
            "2" -> {
                println("--------------- Crear usuario ---------------")
                println("Ingresa el id del usuario:")
                val userId = readLine()!!.toInt()
                println("Ingresa el nombre del usuario:")
                val name = readLine()!!
                println("Ingresa el correo del usuario:")
                val email = readLine()!!
                createUser(userId, name, email)
            }
            "3" -> {

            }
            "4" -> {

            }
            "5" -> {
                printAllUser()
            }
            "6" -> {
                println("Bye bye :D")
                return
            }
            else -> println("Opción no válida")
        }
        printMenu()
    }
}

fun createUser(id: Int, name: String, email: String) {
    val existingUser = findUser(id)
    if (existingUser != null){
        println("Ya existe un usuario registrado con ese id")
        return
    }

    val user = User().apply {
        this.id = id
        this.name = name
        this.email = email
    }
    users.add(user)
    println("Usuario Creado")
}

fun findUser(id: Int): User? {
    return users.find { it.id == id }
}

fun printAllUser(){
    if (users.isEmpty()) {
        println("No hay usuarios registrados")
        return
    }
    println("--------------- Lista de usuarios: ---------------")
    users.forEach { user ->
        with(user) { // accedo a las propiedades sin repetir user.
            println("""
                ID: $id
                Nombre: $name
                Correo: $email
                
            """.trimIndent())
        }
    }
    val usersAmount = users.run { "Total de usuarios: ${size}" }
    println(usersAmount)
}

fun printMenu(){
    println("""
        --------------- GESTIÓN DE USUARIOS ---------------
        1. Buscar usuario
        2. Crear usuario
        3. Editar usuario
        4. Eliminar usuario
        5. Mostrar todos los usuarios
        6. Salir
    """.trimIndent())
}
class User(var id: Int = 0, var name: String = "", var email: String = "")