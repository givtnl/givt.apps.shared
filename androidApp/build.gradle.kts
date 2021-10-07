plugins {
    id("com.android.library")
    kotlin("android")
}
android {
    compileSdkVersion(31)
    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(31)
    }
}
dependencies {
    implementation(project(":GivtCodeShare"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
}