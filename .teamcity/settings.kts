import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {

    vcsRoot(Repo2)

    buildType(Build1)

    subProject(Project2)
}

object Build1 : BuildType({
    name = "build1"

    vcs {
        root(DslContext.settingsRoot)
        root(Repo2)
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "ls"
        }
    }
})

object Repo2 : GitVcsRoot({
    name = "repo2"
    url = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    branch = "refs/heads/master"
})


object Project2 : Project({
    name = "project2"

    subProject(Project2_Project3)
})


object Project2_Project3 : Project({
    name = "project3"
})
