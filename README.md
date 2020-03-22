# Streaming with JAX-RS demo

# Run

Run from intellij `StreamingDemoApplication.main()` or build and run jar:

    mvn clean package 
    java -jar target/streamingdemo-1.0-SNAPSHOT.jar

    curl --no-buffer "localhost:8080/stream/simple/lines?items=10" 
    curl --no-buffer "localhost:8080/stream/json?items=10" 
    
    curl --no-buffer "localhost:8080/stream/lines?items=10&buffer=2&sleep=1000" 
    
Legend:

* `items`   - (quantity)     how much items or lines to produce in a response
* `buffer`  - (quantity)     how much items to send before sleeping
* `sleepms` - (milliseconds) how long to sleep after flushing a `buffer` of `items` - simulates a delay
    
# Notice

> jersey buffer is disabled with `ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER` 

This is only for demo purposes so that jersey sends a message to the caller whenever we write to the `OutputStream`.
Otherwise the jersey buffer would have to grow substantially for jersey to flush it.
In production you don't want to manipulate the buffer length unless you know what you are doing.

Remove that config and then check the difference.
   
> `curl` buffer disabled with `--no-buffer`

Otherwise `curl` would wait for the whole message to be streamed and then flush to the console.
Try the commands without that flag and see the difference.

