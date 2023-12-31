plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("stringfog")
}
apply(plugin = "stringfog")
configure<com.github.megatronking.stringfog.plugin.StringFogExtension> {
    implementation = "com.github.megatronking.stringfog.xor.StringFogImpl"
    enable = true
    fogPackages = arrayOf("com.diabeat.ease.pro")
    kg = com.github.megatronking.stringfog.plugin.kg.RandomKeyGenerator()
    mode = com.github.megatronking.stringfog.plugin.StringFogMode.bytes
}
android {
    namespace = "com.diabeat.ease.pro"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.diabeat.ease.pro"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String","privacy","\"https://github.com/dashboard\"")
            buildConfigField("String","policy","\"https://www.google.com/\"")
            buildConfigField("String","clockUrl","\"https://pax.diabetesease.com/now/lyman/tribe\"")
            buildConfigField("String", "updateUrl", "\"https://serial.diabetesease.com/glue/fink/berg\"")
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String","privacy","\"https://github.com/dashboard\"")
            buildConfigField("String","policy","\"https://www.google.com/\"")
            buildConfigField("String","clockUrl","\"https://pax.diabetesease.com/now/lyman/tribe\"")
            buildConfigField("String", "updateUrl", "\"https://test-serial.diabetesease.com/basso/profit\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //gson
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    //stringfog
    implementation("com.github.megatronking.stringfog:xor:5.0.0")
    implementation("com.squareup.okhttp3:okhttp:3.2.0")
}