plugins {
    id(Plugins.COMMON)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.gkp.core"
}

dependencies {

    // compose
    compose()

    // hilt
    hilt()

    // room
    room()

    // test
    testDependencies()
}
