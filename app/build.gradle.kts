import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "pe.gob.fondepes.demo.portal.certificaciones"
    compileSdk = 34

    defaultConfig {
        applicationId = "pe.gob.fondepes.demo.portal.certificaciones"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        val localProperties = Properties()
        file("local.properties").takeIf { it.exists() }?.inputStream()?.use {
            localProperties.load(it)
        }

        val firebaseApiKey = localProperties.getProperty("firebaseApiKey") ?: ""

        debug {
            buildConfigField("String", "FIREBASE_API_KEY", "\"$firebaseApiKey\"")
            isDebuggable = true
        }

        release {
            buildConfigField("String", "FIREBASE_API_KEY", "\"$firebaseApiKey\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.security.crypto.ktx)
    implementation(libs.android.volley)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}