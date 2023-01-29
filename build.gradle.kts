buildscript {
    extra.apply {
        set("compose_version", "1.3.3")
        set("activity_compose", "1.6.1")
        set("lifecycle_runtime_ktx", "2.5.1")
        set("core_ktx", "1.9.0")
        set("material3", "1.1.0-alpha05")
        set("room_version", "2.5.0")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}