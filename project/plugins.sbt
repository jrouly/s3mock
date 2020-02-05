// See https://wiki.audaxhealth.com/display/ENG/Build+Structure#BuildStructure-Localconfiguration
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
resolvers += Resolver.url("Rally Plugin Releases", url("https://artifacts.werally.in/artifactory/ivy-plugins-release"))(Resolver.ivyStylePatterns)
//resolvers += Resolver.url("Rally Plugin Snapshots", url("https://artifacts.werally.in/artifactory/ivy-plugins-snapshot"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.rallyhealth" %% "rally-versioning" % "1.8.0")
addSbtPlugin("com.rallyhealth" %% "rally-sbt-plugin" % "0.18.0")

logLevel := Level.Warn

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.3")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "2.3")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.1")
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")
addSbtPlugin("se.marcuslonnberg" % "sbt-docker" % "1.5.0")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
addSbtPlugin("se.marcuslonnberg" % "sbt-docker" % "1.5.0")
