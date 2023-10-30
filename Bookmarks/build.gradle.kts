plugins {
    id(Plugins.COMMON)
}

android {
    namespace = "com.gkp.bookmarks"
}

dependencies {
    implementation(Dependencies.androidCoreKtx)
    implementation(Dependencies.androidLifecycleKtx)

    // compose
    compose()

    // hilt
    hilt()

    // Core
    coreModule()

    // test
    testDependencies()
}
