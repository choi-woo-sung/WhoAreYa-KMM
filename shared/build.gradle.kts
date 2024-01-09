plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kmp.nativeCoroutines)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../whoAreYaIosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        kotlin.sourceSets.all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.serialization.json)
                implementation(libs.ktor.client.negotiation)
                implementation(libs.koin.core)
//                implementation(libs.koin.test)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }
    }
}

android {
    namespace = "goal.hyunwoo.whoareya"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}

task("testClasses").doLast {
    println("This is a dummy testClasses task")
}
