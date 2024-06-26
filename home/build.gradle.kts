

plugins {
    id(Plugins.COMMON)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("de.mannodermaus.android-junit5") version "1.9.3.0"
}

android {
    namespace = "com.gkp.home"
}

dependencies {
    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.androidLifecycleKtx)

    // compose
    compose()

    // retrofit
    retrofit()

    // coroutine
    implementation(Dependencies.androidCoroutine)

    // hilt
    hilt()

    // Module
    coreModule()

    // test
    testDependencies()
}
