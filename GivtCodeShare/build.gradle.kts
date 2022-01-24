plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Givt Code Share"
        homepage = "https://github.com/givtnl/givt.apps.shared"
        ios.deploymentTarget = "11.0"
        framework {
            baseName = "GivtCodeShare"
        }
    }

    val datetimeVersion = "0.3.2"
    val ktorVersion = "1.6.7"
    val kotlinSerializationVersion = "1.3.2"
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")

                // Ktor networking
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                // Ktor serialization
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                // Kotlin serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion")            }
        }
        val commonTest by getting {
            dependencies {
                implementation("de.jodamob.kotlin:kotlin-runner-junit4:0.3.1")
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosSimulatorArm64Main by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val iosSimulatorArm64Test by getting
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}