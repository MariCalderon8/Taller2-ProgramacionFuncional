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
**Ejemplo 1**
```kotlin
// Inmutable - usando val
val tareas = listOf("Estudiar Kotlin", "Hacer ejercicio", "Leer libro")
val tareasActualizadas = tareas + "Programar app" // Crea una nueva lista

// Evitar - mutable
var tareasMutable = mutableListOf("Estudiar Kotlin", "Hacer ejercicio", "Leer libro")
tareasMutable.add("Programar app") // Modifica la lista original

data class Tarea(val descripcion: String, val completada: Boolean)
val tarea = Tarea("Estudiar Kotlin", false)
val tareaCompletada = tarea.copy(completada = true)

```
**Ejemplo 2**
```kotlin
// Inmutable - usando val
val materias = listOf("Matemáticas", "Física", "Programación")
val materiasNuevas = materias + "Base de Datos" // Crea una nueva lista

// Evitar - mutable  
var materiasMutable = mutableListOf("Matemáticas", "Física", "Programación")
materiasMutable.add("Base de Datos") // Modifica la lista original

data class Estudiante(val nombre: String, val semestre: Int, val promedio: Double)
val estudiante = Estudiante("Ana", 3, 4.2)
val estudianteActualizado = estudiante.copy(semestre = 4, promedio = 4.5)
```
## Funciones Puras

**Ejemplo 1**
Una función pura siempre devuelve el mismo resultado cuando se le da el mismo argumento.  En este caso, si enviamos `19` como argumento, el resultado siempre será `true`.  
Si enviamos `5`, el resultado siempre será `false`.
```kotlin
fun esMayorDeEdad(edad:Int): Boolean = edad >= 18
```
**Ejemplo 2**
Lo mismo ocurre en el siguiente ejemplo: el factorial de un número siempre será el mismo mientras el argumento no cambie. Si ejecutamos `factorial(5)` siempre obtendremos `120`.
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
fun addStringToGlobal(newString: String){  
    global = "$global $newString"  
    println(global) 
}  
addStringToGlobal("Mundo") // Imprime "Hola Mundo"
addStringToGlobal("Mundo") // Imprime "Hola Mundo Mundo"
```
## Expresiones Lambda
**Ejemplo 1:**
Las expresiones lambda permiten definir funciones de forma anónima (sin nombre) y asignarlas a variables, así la función puede reutilizarse como si fuera una función normal.
```kotlin
val concatenar = { a: String, b: String -> "$a $b" }  
println(concatenar("Texto","Concatenado"))
```
En este ejemplo, `concatenar` actúa como una función que recibe dos `String` y retorna la unión de ambos.

**Ejemplo 2:**
En este ejemplo, la lambda `calcularPromedio` recibe una lista de enteros, calcula la suma de todos los elementos y la divide entre la cantidad de elementos.
```kotlin
val calcularPromedio = { numeros: List<Int> ->
    val suma = numeros.sum()
    val cantidad = numeros.size
    suma / cantidad
}
println(calcularPromedio(listOf(10, 20, 30, 40))) // 25
```
Las expresiones lambda también pueden pasarse por parámetros (funciones de orden superior)

## Funciones de Orden Superior
**Ejemplo 1**
```kotlin

```
**Ejemplo 2**
```kotlin

```
## Evaluación perezosa (Lazy Evaluation)
**Ejemplo 1**
```kotlin

```
**Ejemplo 2**
```kotlin

```
## Composición de Funciones
**Ejemplo 1**
```kotlin

```
**Ejemplo 2**
```kotlin

```
