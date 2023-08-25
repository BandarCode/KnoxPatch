// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.10-1.0.13")
        classpath("dev.rikka.tools.refine:gradle-plugin:4.3.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jcenter.bintray.com")
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
