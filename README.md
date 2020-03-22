# Streaming with JAX-RS demo

## Run

Run from intellij `StreamingDemoApplication.main()` or build and run jar:

    mvn clean package 
    java -jar target/streamingdemo-1.0-SNAPSHOT.jar

    curl --no-buffer "localhost:8080/stream/simple/lines?items=10" 
    curl --no-buffer "localhost:8080/stream/simple/json?items=10" 
    
    curl --no-buffer "localhost:8080/stream/advanced/lines?items=14&buffer=3&sleep=1000" 
    curl --no-buffer "localhost:8080/stream/advanced/json?items=14&buffer=3&sleep=1000" 
    
Legend:

* `items`   - (quantity)     how much items or lines to produce in a response
* `buffer`  - (quantity)     how much items to send before sleeping
* `sleepms` - (milliseconds) how long to sleep after flushing a `buffer` of `items` - simulates a delay
    
## Interesting things

### Changing Jersey buffer size

Jersey buffer is disabled with `ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER=0`

This is only for demo purposes so that jersey sends a message to the caller whenever we write even the smallest
message to the buffer.
Otherwise the jersey buffer would have to grow substantially for jersey to flush it.
In production you don't want to manipulate the buffer length unless you know what you are doing.

Remove or change the value in `StreamingDemoApplication` and then check the difference in behaviour.

From Jersey source:

```
OUTBOUND_CONTENT_LENGTH_BUFFER

An integer value that defines the buffer size used to buffer server-side response entity in order to determine its 
size and set the value of HTTP "Content-Length"  header.
If the entity size exceeds the configured buffer size, the buffering would be cancelled and the entity size 
would not be determined. Value less or equal to zero disable the buffering of the entity at all.
This property can be used on the server side to override the outbound message buffer size value - default or the global 
custom value set using the "jersey.config.contentLength.buffer" global property.

The default value is 8192.
```
   
### Disabling curl buffer

> `curl` buffer disabled with `--no-buffer`

Otherwise `curl` would wait for the whole message to be streamed and then flush to the console.
Try the commands without that flag and see the difference.

Using tools like Postman or Insomnia to make the calls will not work.

### Chunking and content length

Run:

    curl --verbose --no-buffer "localhost:8080/stream/simple/lines?items=10" 
    
Then change the value of the `OUTBOUND_CONTENT_LENGTH_BUFFER` property in `StreamingDemoApplication.java` and run again:

    curl --verbose --no-buffer "localhost:8080/stream/simple/lines?items=10" 

Notice `Transfer-Encoding: chunked` and `Content-Length: n` properties on both calls.

### Control endpoint

To compare responses with a "non streaming" endpoint run:

    curl --verbose "localhost:8080/stream/control" 
    curl --verbose --no-buffer "localhost:8080/stream/control" 
    
