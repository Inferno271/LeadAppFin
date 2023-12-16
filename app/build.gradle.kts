plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.inferno271.leadappfin"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8" // Обновленная версия
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "com.inferno271.leadappfin"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.22")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui:1.5.3") // Обновленная версия
    implementation("androidx.compose.ui:ui-graphics:1.5.3") // Обновленная версия
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3") // Обновленная версия
    implementation("androidx.compose.material3:material3:1.1.2") // Обновленная версия
    implementation("androidx.appcompat:appcompat:1.6.1")


    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.ui:ui-tooling-data-android:1.5.3")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")


    //  implementation("com.google.firebase:firebase-inappmessaging:20.3.1")

//    implementation("androidx.recyclerview:recyclerview:1.3.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.3") // Обновленная версия
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3") // Обновленная версия
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3") // Обновленная версия






}
