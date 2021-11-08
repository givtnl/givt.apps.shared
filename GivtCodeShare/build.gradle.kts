import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
repositories {
    maven { url = uri("https://repo.repsy.io/mvn/chrynan/public") }
}
kotlin {

    android()
    ios {
        binaries {
            framework {
                baseName = "GivtCodeShare"
            }
        }
    }
    val chrynanValidatorVersion = "0.4.1"
    val datetimeVersion = "0.2.1"
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.chrynan.validator:validator-phone:$chrynanValidatorVersion")
                implementation("com.chrynan.validator:validator-email:$chrynanValidatorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")
            }
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
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(31)
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)