pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
//        maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/google") }
//        maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/jcenter") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/google") }
//        maven { setUrl("http://maven.aliyun.com/nexus/content/repositories/jcenter") }
    }
}

rootProject.name = "bingdouscript"
include(":app")
 