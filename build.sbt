import AssemblyKeys._

assemblySettings

name := "SparkExamples"

version := "1.0"

scalaVersion := "2.10.4"

jarName in assembly := "spark-examples.jar"

assemblyOption in assembly ~= { _.copy(includeScala = false) }

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.10" % "1.1.1" %"provided",
  "org.apache.spark" % "spark-sql_2.10" % "1.1.1" % "provided",
  "org.apache.spark" % "spark-mllib_2.10" % "1.1.1" % "provided",
  "org.apache.spark" % "spark-streaming_2.10" % "1.1.1"%"provided",
  "org.apache.kafka" % "kafka_2.10" % "0.8.2-beta",
  "org.twitter4j" % "twitter4j-stream" % "3.0.3" withJavadoc(),
  "com.typesafe.play" % "play-json_2.10" % "2.2.1",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.3.3",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.3.3",
  "org.apache.spark" %% "spark-streaming" % "1.0.0" % "provided",
  "org.apache.spark" % "spark-streaming-twitter_2.11" % "1.2.0",
  "org.apache.cassandra" % "cassandra-all" % "2.0.8"
    exclude("com.google.guava", "guava") ,
  "org.apache.cassandra" % "cassandra-thrift" % "2.0.8"
    exclude("com.google.guava", "guava") ,
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.0.8"
    exclude("com.google.guava", "guava")
)

resolvers ++= Seq(
  "JBoss Repository" at "http://repository.jboss.org/nexus/content/repositories/releases/",
  "Spray Repository" at "http://repo.spray.cc/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Twitter4J Repository" at "http://twitter4j.org/maven2/",
  "Apache HBase" at "https://repository.apache.org/content/repositories/releases",
  "Twitter Maven Repo" at "http://maven.twttr.com/",
  "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven",
  Resolver.sonatypeRepo("public")
)






    