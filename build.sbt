name := "slick-scalaz"

scalaVersion := "2.11.8"

resolvers += "bintray/non" at "http://dl.bintray.com/non/maven"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"       % "3.1.1",
  "org.scalaz"         %% "scalaz-core" % "7.2.6",
  "org.scalatest"      %% "scalatest"   % "2.2.1" % "test"
)

addCompilerPlugin("org.spire-math" % "kind-projector_2.11" % "0.5.2")
