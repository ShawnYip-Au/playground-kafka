# playground-kafka

1. Start Kafka inside Docker: ```docker compose up -d```
    - Use *docker-compose.yml* from the current directory
    - Start the service(s) in the background (detached)
    - Check that Kafka container is running: ```docker ps```
    - https://github.com/apache/kafka/tree/trunk/docker/examples

2. Create a topic via a *broker* (optional)
    ```
    # -------------------------------------------
    # Command to create a Kafka topic inside a Docker container
    # 
    # Steps:
    # 1. Runs 'kafka-topics.sh' inside running 'kafka' container
    # 2.     Create a new topic
    # 3.     Topic name
    # 4.     Kafka broker to connect to
    # 5.     Number of partitions for parallelism
    # 6.     Number of broker replicas
    # -------------------------------------------

    docker exec -it kafka opt/kafka/bin/kafka-topics.sh \
        --create \
        --topic my-topic \
        --bootstrap-server localhost:9092 \
        --partitions 3 \
        --replication-factor 1


    docker exec -it kafka opt/kafka/bin/kafka-topics.sh \
        --list \
        --bootstrap-server localhost:9092

    docker exec -it kafka opt/kafka/bin/kafka-topics.sh \
        --describe \
        --topic my-topic \
        --bootstrap-server localhost:9092
    ```

3. Start producer
    - `toto-jackpot-producer` > `./gradlew bootRun`
    - Swagger Ui: http://localhost:8080/swagger-ui/index.html
