import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSDK = 34
    const val minSDK = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    val javaVersion = JavaVersion.VERSION_17
    const val javaTarget = "17"
    const val composeKotlinCompilerExt = "1.4.3"
}