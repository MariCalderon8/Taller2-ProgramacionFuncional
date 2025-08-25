# Taller 2 - ProgramacionFuncional
**Presentado Por:**

- Mariluz Calderón Guevara
- Maria Camila Castro
- Sebastián Botero


### Ejemplos de los Pilares de la programación funcional

1. [Inmutabilidad](#inmutabilidad)
2. [Funciones Puras](#funciones-puras)
3. [Expresiones Lambda](#expresiones-lambda)
4. [Funciones de Orden Superior](#funciones-de-orden-superior)
5. [Evaluación Perezosa (Lazy Evaluation)](#evaluación-perezosa-lazy-evaluation)
6. [Composición de Funciones](#composición-de-funciones)

## Inmutabilidad

La inmutabilidad se refiere a que un dato no puede modificarse una vez creado. Si se necesita un cambio, no se altera el objeto original, sino que se genera una nueva versión con las modificaciones aplicadas.

El ejemplo más simple de inmutabilidad en Kotlin lo podemos encontrar con el uso de las variables:

    val → inmutable (no se puede reasignar valores).
    var → mutable (se puede reasignar valores).

**Ejemplo 1**

Se crea una lista inmutable de `tareas` usando val y listOf. Al agregar una nueva tarea, `no se modifica la lista original`, sino que se genera una `nueva lista` con el elemento añadido.
```kotlin
// Inmutable - usando val
val tareas = listOf("Estudiar Kotlin", "Hacer ejercicio", "Leer libro")
val tareasActualizadas = tareas + "Programar app" // Crea una nueva lista
```
Se define la clase de datos `Tarea` para representar una tarea con su descripción y su estado de completada. Al `marcar una tarea como realizada`, en lugar de cambiar el objeto original, se utiliza la función `copy` para crear una `nueva instancia` con el valor actualizado.
```kotlin
data class Tarea(val descripcion: String, val completada: Boolean)
val tarea = Tarea("Estudiar Kotlin", false)
val tareaCompletada = tarea.copy(completada = true)

```
**Ejemplo 2**

La lista `materias` se declara con val, por lo que no puede modificarse directamente. Al `añadir "Base de Datos"`, no se cambia la lista original, sino que se crea una nueva lista (`materiasNuevas`) que incluye el nuevo elemento junto con los anteriores.
```kotlin
// Inmutable - usando val
val materias = listOf("Matemáticas", "Física", "Programación")
val materiasNuevas = materias + "Base de Datos" // Crea una nueva lista

```
Se define una clase de datos `Estudiante` para representar a un estudiante con su nombre, semestre y promedio. Al crear un objeto estudiante, se `almacenan sus datos iniciales`. Luego, en lugar de modificar directamente ese objeto, se usa la función `copy` para generar una nueva instancia (`estudianteActualizado`) con los valores actualizados en el semestre y el promedio
```kotlin
data class Estudiante(val nombre: String, val semestre: Int, val promedio: Double)
val estudiante = Estudiante("Ana", 3, 4.2)
val estudianteActualizado = estudiante.copy(semestre = 4, promedio = 4.5)
```
## Funciones Puras

Las funciones puras son aquellas que siempre devuelve el mismo resultado cuando se le da el mismo argumento, no modifica variables externas, no imprime por pantalla, no escribe en un archivo, etc.

**Ejemplo 1**

En este caso, si enviamos `19` como argumento, el resultado siempre será `true`.  
Si enviamos `5`, el resultado siempre será `false`.
```kotlin
fun esMayorDeEdad(edad:Int): Boolean = edad >= 18
```
**Ejemplo 2**

El factorial de un número siempre será el mismo mientras el argumento no cambie. Si ejecutamos `factorial(5)` siempre obtendremos `120`.
```kotlin
fun factorial(num: Int): Int {  
    var resultado = 1  
  for (i in 1..num) {  
        resultado *= i  
    }  
    return resultado  
}
```
En una función impura, aunque el parámetro sea el mismo, el resultado puede variar o puede generar efectos secundarios.
Por ejemplo, esta función aunque recibe el mismo argumento, el resultado es diferente cada vez:
```kotlin
var global: String = "Hola"  
fun añadirStringAGlobal(nuevoString: String){  
    global = "$global $nuevoString"  
    println(global) 
}  
añadirStringAGlobal("Mundo") // Imprime "Hola Mundo"
añadirStringAGlobal("Mundo") // Imprime "Hola Mundo Mundo"
```
## Expresiones Lambda

Las expresiones lambda permiten definir funciones de forma anónima (sin nombre) y asignarlas a variables o pasar como argumento a otra función.

**Ejemplo 1**

En este ejemplo se define una expresión lambda llamada `concatenar`, que recibe dos cadenas (`a` y `b`) y devuelve una nueva cadena con ambas unidas y separadas por un espacio. Al invocar `concatenar("Texto", "Concatenado")`, el resultado es la cadena `"Texto Concatenado"`.
```kotlin
val concatenar = { a: String, b: String -> "$a $b" }  
println(concatenar("Texto","Concatenado"))
```

**Ejemplo 2**

En este ejemplo se define una expresión lambda llamada `calcularPromedio`, que recibe una lista de enteros (`List<Int>`). Dentro de la lambda, se obtiene la `suma` de los elementos y la `cantidad` de valores, y luego se retorna el resultado de `suma / cantidad`. Al invocar `calcularPromedio(listOf(10, 20, 30, 40))`, el resultado es `25`.
```kotlin
val calcularPromedio = { numeros: List<Int> ->
    val suma = numeros.sum()
    val cantidad = numeros.size
    suma / cantidad
}
println(calcularPromedio(listOf(10, 20, 30, 40))) // 25
```

## Funciones de Orden Superior

Una función de orden superior es aquella que recibe otra función por parámetro o devuelve otra función como resultado.
**Ejemplo 1**

En las funciones de orden superior se puede enviar otra función por argumento. En este caso la función `filtrarLista` recibe por parámetros una lista de números y una función como condición para filtrarlos (si son pares).
```kotlin
fun esPar(n: Int) = n % 2 == 0

fun filtrarLista(numbers: List<Int>, condition: (Int) -> Boolean): List<Int> {
    return numbers.filter(condition)
}

val nums = listOf(1, 2, 3, 4, 5, 6)
val pares = filtrarLista(nums, ::esPar)
println("Números pares: $pares")
```

**Ejemplo 2**

Estas funciones también pueden devolver otra función. En este caso la función `saludar` devuelve otra función que imprime el saludo que se envío por parametro a la función de orden superior y el nombre que recibe por parámetro la otra función. Al guardar el retorno en una variable y al llamar esa variable estaría invocando la función del retorno.
```kotlin
fun saludar(saludo: String): (String) -> Unit {
    return { nombre ->
        println("$saludo, $nombre!")
    }
}

val sayHello = saludar("Hola")
val sayWelcome = saludar("Bienbenido")

sayHello("Pepe")   // imprime: Hola, Pepe!
sayWelcome("Fulano")   // imprime: Bienvenido, Fulano!
```

## Evaluación perezosa (Lazy Evaluation)

La evaluación perezosa no calcúla los valores inmediatamente sino solo cuando realmente se necesitan.
**Ejemplo 1**

En este ejemplo se usa evaluación perezosa para transformar y filtrar una lista. Primero convierte cada nombre a mayúscula y luego filtra los que empiezan por "A". Sin embargo, estas operaciones no se ejecutan inmediatamente, sino hasta que realmente se solicita el resultado con `toList()`
```kotlin
val nombres = listOf("Ana", "Pedro", "Lucía", "Juan", "Antonio")
    .asSequence()
    .map {
        println("Procesando $it")
        it.uppercase()
    }
    .filter {
        println("Verificando $it")
        it.startsWith("A")
    }

println("Lista lista, aún no procesada")
println("Resultados finales: ${nombres.toList()}") // ['ANA','ANTONIO']
```
**Ejemplo 2**

En este caso, la secuencia busca la primera galleta de chocolate.
Gracias a la evaluación perezosa, no revisa toda la lista, sino que se detiene en cuanto encuentra la primera coincidencia.
```kotlin
val galletas = listOf("vainilla", "fresa", "chocolate", "limón", "chocolate")
val primeraChocoSecuencia = galletas
    .asSequence()
    .map {
        println("Revisando galleta: $it")
        it
    }
    .first { it == "chocolate" }
println("Primera galleta de chocolate: $primeraChocoSecuencia")
```
## Composición de Funciones

La composición de funciones es combinar varias funciones pequeñas para formar una nueva función.
**Ejemplo 1**

En este ejemplo, se crean funciones de extensión pequeñas `(tieneArroba, tienePunto, noEstaVacio)` que se combinan en validarEmail para crear una validación compleja. También se muestra procesarEmail que encadena operadores para limpiar el email paso a paso.
```kotlin
// Composición con funciones de extensión:
fun String.tieneArroba(): Boolean = this.contains("@")
fun String.tienePunto(): Boolean = this.contains(".")
fun String.noEstaVacio(): Boolean = this.isNotEmpty()

fun validarEmail(email: String): Boolean {
    return email.noEstaVacio() && email.tieneArroba() && email.tienePunto()
}

// Composición con operadores:
val procesarEmail = { email: String ->
    email.trim()
        .lowercase()
        .replace(" ", "")
        .takeIf { it.contains("@") && it.contains(".") }
        ?: "email_invalido@ejemplo.com"
}
```
**Ejemplo 2**

En este ejemplo, las funciones de extensión `esPositivo`, `esPar` y `duplicar` se combinan en procesarNumero para clasificar números según sus propiedades. La función transformarNumeros encadena operadores para filtrar números positivos, elevarlos al cuadrado, quedarse solo con los pares, ordenarlos y tomar los primeros 5.
```kotlin
// Composición con funciones de extensión:
fun Int.esPositivo(): Boolean = this > 0
fun Int.esPar(): Boolean = this % 2 == 0
fun Int.duplicar(): Int = this * 2

fun procesarNumero(numero: Int): String {
    return when {
        numero.esPositivo() && numero.esPar() -> "Número par positivo: ${numero.duplicar()}"
        numero.esPositivo() -> "Número impar positivo: $numero"
        else -> "Número no válido"
    }
}

// Composición con operadores:
val transformarNumeros = { numeros: List<Int> ->
    numeros.filter { it > 0 }
        .map { it * it }
        .filter { it % 2 == 0 }
        .sorted()
        .take(5)
}
```
