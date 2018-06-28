package dc.hack.db;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class dbConnection {


    public static void main(String args[]){

        System.out.println("Connecting to postgres db");
    }


    public static Connection getConnection() {
        Connection dbConn = null;
        String dbName = "DB_NAME";
        String userName ="RDS_USERNAME";
        String password = "RDS_PASSWORD";
        String hostname = "RDS_HOSTNAME";
        String port = "RDS_PORT";
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
        String DB_URL = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName;


        try {
            Class.forName("org.postgresql.Driver");
            dbConn = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }

        return dbConn;
    }

    public static DynamoDB getDynamoDbConnection(){

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);


        /*
        BasicAWSCredentials myCredentials = new BasicAWSCredentials("id", "key");
        AmazonDynamoDB dynaClient = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(myCredentials))
                .withRegion("us-west-2").build();
           */


        return dynamoDB;

    }

    public void retrieveData(DynamoDB dynamoDB)throws Exception{

        Table table = dynamoDB.getTable("Movies");

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#yr", "year");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":yyyy", 1985);

        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
                .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        try {
            System.out.println("Movies from 1985");
            items = table.query(querySpec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                System.out.println(item.getNumber("year") + ": " + item.getString("title"));
            }

        }
        catch (Exception e) {
            System.err.println("Unable to query movies from 1985");
            System.err.println(e.getMessage());
        }


    }

}
