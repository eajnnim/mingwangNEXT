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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
<<<<<<< HEAD
rootProject.name = "MarketClean"
=======
rootProject.name = "My Application4"
>>>>>>> 04c7e34d0a2010c4fa2396c1bccae9bdb7913f17
include(":app") // <- 반드시 필요!

