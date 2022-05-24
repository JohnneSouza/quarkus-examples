package org.acme.core.config;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

public class MongoResource implements QuarkusTestResourceLifecycleManager {

    final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    {
        mongoDBContainer.start();
    }

    @Override
    public Map<String, String> start() {
        Map<String, String> registry = new HashMap<>();
        registry.put("quarkus.mongodb.connection-string", mongoDBContainer.getReplicaSetUrl());
        registry.put("quarkus.mongodb.database", "IntegrationTests");
        return registry;
    }

    @Override
    public void stop() {
        mongoDBContainer.stop();
    }
}
