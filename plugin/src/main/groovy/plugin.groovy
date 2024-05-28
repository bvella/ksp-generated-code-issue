import java.nio.file.Files
import java.nio.file.Paths

project.afterEvaluate {
    def generateTask = project.tasks.register('generate') {
        outputs.cacheIf { false }

        project.sourceSets.main.kotlin.srcDir "${project.layout.buildDirectory.get()}/generated/sources/pp/kotlin/main"

        doFirst {
            project.delete "${project.layout.buildDirectory.get()}/generated/sources/pp/kotlin/main"
            project.delete "$project.projectDir/src/main/kotlin/example/ServiceClient.kt"

            Files.createDirectories(Paths.get("${project.layout.buildDirectory.get()}/generated/sources/pp/kotlin/main/example"))
            Files.createFile(Paths.get("${project.layout.buildDirectory.get()}/generated/sources/pp/kotlin/main/example/ServiceClient.kt"))

            new FileOutputStream(
                "${project.layout.buildDirectory.get()}/generated/sources/pp/kotlin/main/example/ServiceClient.kt"
//                "$project.projectDir/src/main/kotlin/example/ServiceClient.kt"
            ).write(
                "package example\n@jakarta.inject.Singleton class ServiceClient { fun something() { println(\"something\") } }".getBytes()
            )
        }
    }

    project.tasks.kspKotlin.dependsOn generateTask
}
