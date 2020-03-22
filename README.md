# Streaming with JAX-RS demo

    mvn clean package 
    java -jar target/streamingdemo-1.0-SNAPSHOT.jar
    
    curl --no-buffer "localhost:8080/stream?items=100" 
    
Notice the `--no-buffer` - otherwise `curl` will wait for the full response before flushing it to the console.

