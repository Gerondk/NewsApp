import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.implementation( dependency: String) {
    add("implementation",dependency)
}
fun DependencyHandler.platformImplementation( dependency: String) {
    add("implementation",platform(dependency))
}

fun DependencyHandler.test( dependency: String) {
    add("testImplementation", dependency)
}
fun DependencyHandler.androidTest( dependency: String) {
    add("androidTest", dependency)
}
fun DependencyHandler.ksp( dependency: String) {
    add("ksp", dependency)
}
fun DependencyHandler.anotationProcessor( dependency: String) {
    add("annotationProcessor", dependency)
}

fun DependencyHandler.kapt( dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.moduleImplementation(dependencyModule: String) {
    add("implementation", project(dependencyModule))
}
