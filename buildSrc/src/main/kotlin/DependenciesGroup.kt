import org.gradle.api.artifacts.dsl.DependencyHandler

val roomDependenciesList = setOf(Dependencies.roomRuntime,Dependencies.roomCorountine)

fun DependencyHandler.room() {
    roomDependenciesList.forEach {
        implementation(it)
    }
    anotationProcessor(Dependencies.roomCompiler)
    ksp(Dependencies.roomCompiler)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

val retrofitDependenciesList = setOf(
    Dependencies.okhHttp3,
    Dependencies.okhHttp3Interceptor,
    Dependencies.gson,
    Dependencies.gsonSquare,
    Dependencies.retrofit
)
fun DependencyHandler.retrofit() {
    retrofitDependenciesList.forEach {
        implementation(it)
    }
}

val composeDependenciesList = setOf(
    Dependencies.androidActivityCompose,
    Dependencies.composeUi,
    Dependencies.composeUiGraphics,
    Dependencies.composeUiToolingPreview,
    Dependencies.composeMaterial3,
    Dependencies.composeCoreIcons,
    Dependencies.composeExtendedIcons,
    Dependencies.composeNavigation,
    Dependencies.composeRuntineLifecycle,
    Dependencies.composeViewModel,
    Dependencies.composeHiltNavigation,
    Dependencies.composeCoil
)
fun DependencyHandler.compose() {
    platformImplementation(Dependencies.composeBom)
    composeDependenciesList.forEach {
        implementation(it)
    }
}

fun DependencyHandler.coreModule() {
    moduleImplementation(Modules.core)
}

fun DependencyHandler.bookmarksModule() {
    moduleImplementation(Modules.bookmarks)
}

fun DependencyHandler.homeModule() {
    moduleImplementation(Modules.home)
}

val junit5Tests = setOf(
    Dependencies.junit5Api,
    Dependencies.junit5Params,
    Dependencies.assertK,
    Dependencies.androidCoroutineTest,
    Dependencies.turbine,
    Dependencies.mockk,
    Dependencies.jUnit4
)
fun DependencyHandler.testDependencies() {
    junit5Tests.forEach {
        test(it)
    }
    runtimeOnlyTest(Dependencies.junit5Engine)
    runtimeOnlyTest(Dependencies.jUnit4Runtime)
    debug(Dependencies.composeTest)
    androidTest(Dependencies.androidTest)
}