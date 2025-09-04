import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    id("co.touchlab.skie") version "0.10.6"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions { jvmTarget.set(JvmTarget.JVM_11) }
    }

    val iosX64Target = iosX64()
    val iosArm64Target = iosArm64()
    val iosSimArm64Target = iosSimulatorArm64()

    listOf(iosX64Target, iosArm64Target, iosSimArm64Target).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
                implementation(libs.ktor.client.core)
                implementation("io.ktor:ktor-client-content-negotiation:3.2.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
                implementation(libs.kotlinx.datetime)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")
                implementation(libs.ktor.client.okhttp)
            }
        }

        val iosMain by creating { dependsOn(commonMain) }
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }

        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:3.2.3")
        }

        val commonTest by getting {
            dependencies { implementation(libs.kotlin.test) }
        }
    }
}

android {
    namespace = "com.example.dailypulse"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
