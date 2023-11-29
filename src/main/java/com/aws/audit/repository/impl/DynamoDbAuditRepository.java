package com.aws.audit.repository.impl;

import com.aws.audit.dto.SearchCriteria;
import com.aws.audit.entity.Audit;
import com.aws.audit.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.internal.waiters.ResponseOrException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Slf4j
public class DynamoDbAuditRepository implements AuditRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<Audit> auditTable;


    @Autowired
    public DynamoDbAuditRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        auditTable = createTableIfNotExist(dynamoDbEnhancedClient);
    }

    public void save(Audit audit) {
        auditTable.putItem(audit);
    }

    private DynamoDbTable<Audit> createTableIfNotExist(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        boolean isExist = false;
        auditTable = dynamoDbEnhancedClient.table("Audit", TableSchema.fromBean(Audit.class));
        try {
            auditTable.describeTable();
            isExist = true;
        } catch (ResourceNotFoundException notFoundException) {
            log.info("Audit table already exist");
        } catch (Exception ex) {
            log.error("Unknown error in describing the table", ex);
        }
        if (!isExist) {
            return createTable(dynamoDbEnhancedClient);
        }
        return auditTable;
    }

    private DynamoDbTable<Audit> createTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        // Create the table
        auditTable = dynamoDbEnhancedClient.table("Audit", TableSchema.fromBean(Audit.class));
        auditTable.createTable(builder -> builder
                .provisionedThroughput(b -> b
                        .readCapacityUnits(2L)
                        .writeCapacityUnits(2L)
                        .build())
        );

        log.info("Waiting for table creation...");

        try (DynamoDbWaiter waiter = DynamoDbWaiter.create()) { // DynamoDbWaiter is Autocloseable
            ResponseOrException<DescribeTableResponse> response = waiter
                    .waitUntilTableExists(builder -> builder.tableName("Audit").build())
                    .matched();
            DescribeTableResponse tableDescription = response.response().orElseThrow(
                    () -> new RuntimeException("Audit table was not created."));
            // The actual error can be inspected in response.exception()
            log.info(tableDescription.table().tableName() + " was created.");
        }
        return auditTable;
    }

    public List<Audit> search(SearchCriteria searchCriteria) {
        //TODO: based on filter criteria fetch the record from DB.
        List<Audit>auditList = new ArrayList<>();
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder()
                        .partitionValue(searchCriteria.getAppId())
                        .build());

        Iterator<Audit> results = auditTable.query(queryConditional).items().iterator();
        while (results.hasNext()){
            auditList.add(results.next());
        }
        return auditList;
    }

}
