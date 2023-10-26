object Dependencies {
    val androidCoreKtx = "androidx.core:core-ktx:${Versions.androidCoreKtx}"
    val androidLifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidLifecycleKtx}"

    val androidActivityCompose = "androidx.activity:activity-compose:${Versions.androidActivityCompose}"
    val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    val composeUi = "androidx.compose.ui:ui"
    val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    val composeMaterial3 = "androidx.compose.material3:material3"
    val composeCoreIcons = "androidx.compose.material:material-icons-core"
    val composeExtendedIcons = "androidx.compose.material:material-icons-extended"
    val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    val composeRuntineLifecycle = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.composeRuntineLifecycle}"
    val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    val composeHiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.composeHiltNavigation}"
    val composeCoil = "io.coil-kt:coil-compose:${Versions.composeCoil}"

    val junit5Compose = "de.mannodermaus.junit5:android-test-compose:${Versions.junit5Compose}"
    val composeTest = "androidx.compose.ui:ui-test-manifest"

    val okhHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3Version}"
    val okhHttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val gsonSquare = "com.squareup.retrofit2:converter-gson:${Versions.gsonSquare}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    val androidCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.androidCoroutine}"
    val androidCoroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.androidCoroutine}"
    //test flows
    val turbine = "app.cash.turbine:turbine:${Versions.turbine}"

    val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"
    val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroid}"


    val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    val roomCorountine = "androidx.room:room-ktx:${Versions.roomVersion}"

    val kspPlugin = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.kspTool}"

    val junit5Api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    val junit5Params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
    val assertK = "com.willowtreeapps.assertk:assertk:${Versions.assertK}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"


}