# playground-kafka

[Kafka Documentation](https://kafka.apache.org/documentation.html)<br>
[Kafka API](https://kafka.apache.org/21/javadoc/org/apache/kafka)<br>
[Spring Kafka Documentation](https://spring.io/projects/spring-kafka#overview)<br>
[Consumer Group IDs](https://www.confluent.io/blog/configuring-apache-kafka-consumer-group-ids)<br>

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
    - Open `toto-jackpot-producer` > `./gradlew bootRun`
    - Swagger Ui: http://localhost:8080/swagger-ui/index.html

4. Start consumer
    - Open `buying-agent-consumer` > `./gradlew bootRun`
    - Alternatively, 
    ```
    docker exec -it kafka opt/kafka/bin/kafka-console-consumer.sh \
        --bootstrap-server localhost:9092 \
        --topic jackpot-topic \
        --from-beginning \
        --property print.partition=true \
        --property print.offset=true \
        --property print.timestamp=true
    ```

## sasl_plaintext/scram authentication (client → broker)

KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT_HOST:SASL_PLAINTEXT
KAFKA_SASL_ENABLED_MECHANISMS: SCRAM-SHA-256
KAFKA_LISTENER_NAME_PLAINTEXT__HOST_SCRAM___SHA___256_SASL_JAAS_CONFIG: 'org.apache.kafka.common.security.scram.ScramLoginModule required;'


```
# Command to create a user w/ (username="mskdev", password="mskdev-secret") inside kafka cluster
#
# Steps:
# 1. Runs 'kafka-topics.sh' inside running 'kafka' container
# 2. Connect to broker's internal listener (KRaft/Admin API)
# 3. perform an alteration (apply the config change)
# 4. add SCRAM-SHA-256 credential with the given password
# 5. the type of entity we are changing (a user principal)
# 6. the username / principal to create or alter
#
/opt/kafka/bin/kafka-configs.sh \
  --bootstrap-server localhost:19092 \
  --alter \
  --add-config 'SCRAM-SHA-256=[password="mskdev-secret"]' \
  --entity-type users \
  --entity-name mskdev

/opt/kafka/bin/kafka-configs.sh \
  --bootstrap-server localhost:19092 \
  --describe \
  --entity-type users \
  --entity-name "mskdev"
```


