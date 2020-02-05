@Library("global-pipeline-libraries") _

def app = "s3mock"

pipeline {
    agent {
        label 'ec2'
    }

    options {
        skipDefaultCheckout false
        timeout(time: 30, unit: 'MINUTES')
        timestamps()
        ansiColor('xterm')
    }

    parameters {
        choice(name: 'release', choices: "rally-versioning\npatch\nminor\nmajor", description: 'Release versioning.')
    }

    triggers {
        issueCommentTrigger('.*test this please.*')
    }

    stages {

        // Test the code and publish the artifact if tests pass.
        stage('test and publish') {
    
            steps {
                script {
                    def isRallyVersioningRelease = params.release == 'rally-versioning'
                    def releaseArg = (isRallyVersioningRelease) ? [:] : ['release': "${params.release}"]

                    rally_sbt(releaseArg, "+clean reload +compile +coverage +test +coverageAggregate +coverageOff +semVerCheck +publish 'writeVersion Version.txt'")

                    env.newVersion = 'v' + readFile('Version.txt').trim()

                    // Publish tags for non rally-versioning releases.
                    if (!isRallyVersioningRelease) {
                        rally_git_pushTag(env.newVersion, "Releasing ${params.release} for ${app} as ${env.newVersion}")
                    }
                }
            }
        }

    }

    post {
        success {
            script {
                currentBuild.description = "[${params.release}] ${env.newVersion}"
            }
        }
        always {
            junit '**/test-reports/*.xml'
            cobertura coberturaReportFile: '**/coverage-report/cobertura.xml'
        }
    }

}
