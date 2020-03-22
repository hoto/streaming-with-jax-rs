# Streaming with JAX-RS demo

Run from intellij `StreamingDemoApplication.main()` or build and run jar:

    mvn clean package 
    java -jar target/streamingdemo-1.0-SNAPSHOT.jar

    curl --no-buffer "localhost:8080/stream/lines?items=10" 
    curl --no-buffer "localhost:8080/stream/json?items=10" 
    
Notice:

* curl buffer disabled with `--no-buffer`
* Jersey buffer disabled with `ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER` 
