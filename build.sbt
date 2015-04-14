name := "slick-scalaz"

scalaVersion := "2.11.6"

resolvers += "bintray/non" at "http://dl.bintray.com/non/maven"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"       % "3.0.0-RC3",
  "org.scalaz"         %% "scalaz-core" % "7.1.1",
  "org.scalatest"      %% "scalatest"   % "2.2.1" % "test"
)

addCompilerPlugin("org.spire-math" % "kind-projector_2.11" % "0.5.2")
