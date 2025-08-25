
val users = mutableListOf<User>()

fun main() {
    printMenu()

    while (true){
        val userResponse = readLine()
        when (userResponse){
            "1" ->{
                println("--------------- Buscar usuario ---------------")
                println("Ingresa el id del usuario:")
                readLine()?.toIntOrNull()?.let { userId ->
                    findUser(userId)?.let { user ->
                        with(user) {
                            println("""
                                    ID: $id
                                    Nombre: $name
                                    Correo: $email
                                """.trimIndent())
                        }
                    } ?: println("No pudimos encontrar el usuario que estabas buscando.")
                } ?: println("ID inválido. Por favor ingresa un número.")
            }

            "2" -> {
                println("--------------- Crear usuario ---------------")
                println("Ingresa el id del usuario:")
                val userId = readLine()?.toIntOrNull()
                println("Ingresa el nombre del usuario:")
                val name = readLine()
                println("Ingresa el correo del usuario:")
                val email = readLine()
                when {
                    userId == null -> println("ID inválido")
                    name.isNullOrBlank() -> println("Nombre no puede estar vacío")
                    email.isNullOrBlank() -> println("Correo no puede estar vacío")
                    !isValidEmail(email) -> println("Formato de correo inválido")
                    else -> createUser(userId, name, email)
                }
            }

            "3" -> {
                println("--------------- Editar usuario (Deje vacío para no editar el campo que aparece en pantalla) ---------------")
                println("Ingresa el ID del usuario a editar:")
                readLine()?.toIntOrNull()?.let { userId ->
                    println("Nuevo nombre:")
                    val name = readLine()
                    println("Nuevo correo:")
                    val email = readLine()
                    println(email)
                    updateUser(userId, name, email)
                } ?: println("ID inválido")
            }

            "4" -> {
                println("--------------- Eliminar usuario ---------------")
                println("Ingresa el ID del usuario a eliminar:")
                readLine()?.toIntOrNull()?.let { userId ->
                    deleteUser(userId)
                } ?: println("ID inválido")
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
    }.also {
        println("Usuario $id creado correctamente")
    }
    users.add(user)
}

fun findUser(id: Int): User? {
    return users.find { it.id == id }
}

fun updateUser(id: Int, newName: String?, newEmail: String?) {
    findUser(id)?.let { user ->
        user.apply {
            newName?.takeIf { it.isNotBlank() }?.let {
                user.name = it
            }
            newEmail?.takeIf { it.isNotBlank() }?.let {
                if (isValidEmail(it)) {
                    user.email = it
                } else {
                    println("Nuevo email no válido: $it")
                }
            }
        }.also {
            println("Usuario ${it.id} editado correctamente")
        }
    } ?: println("Usuario no encontrado")
}

fun deleteUser(id: Int) {
    findUser(id)?.let { user ->
        users.remove(user).also {
            println("Usuario ${user.name} eliminado correctamente")
        }
    } ?: println("Usuario no encontrado")
}

fun printAllUser(){
    if (users.isEmpty()) {
        println("No hay usuarios registrados")
        return
    }
    println("--------------- Lista de usuarios: ---------------")
    users.forEach { user ->
        with(user) { // accede a las propiedades sin repetir user.
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

fun isValidEmail(email: String): Boolean {
    return email.let {
        it.contains("@") && it.contains(".") && it.length > 5
    }
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