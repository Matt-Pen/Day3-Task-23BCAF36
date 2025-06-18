package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Scanner;

public class Student {
public void enroll()
{
    Scanner scan= new Scanner(System.in);
    String uri = "mongodb://localhost:27017/";
    try{

        MongoClient mongoClient= MongoClients.create(uri);
        MongoDatabase database=mongoClient.getDatabase("Student");
        MongoCollection<Document> booksCollection = database.getCollection("Student");
        MongoCollection<Document> booksCollection2 = database.getCollection("Course");
        MongoCollection<Document> booksCollection3 = database.getCollection("enroll");
        MongoCollection<Document> booksCollection4 = database.getCollection("enroll2");
//
//        System.out.println("Enter your Account number: ");
//        String accno= scan.nextLine();
//        double newbal=0;
//        Bson filter2 = Filters.eq("Account num",accno);
//        Bson projection2 = Projections.fields(Projections.include("Balance"));
//        Document doc2= booksCollection.find(filter2).projection(projection2).first();
//        newbal=doc2.getDouble("Balance");
//        System.out.println("Current Balance: "+ newbal );
        Document doc = new Document("name","abhinav").append("phone no","84628371034").append("age",19);
        Document doc2 = new Document("name","Matthew").append("phone no","93648236485").append("age",20);
        Document doc3 = new Document("name","joyal").append("phone no","846283754399").append("age",21);
        booksCollection.insertOne(doc);
        ObjectId ob=doc.getObjectId("_id");

        booksCollection.insertOne(doc2);
        ObjectId ob2=doc2.getObjectId("_id");

        booksCollection.insertOne(doc3);
        ObjectId ob3=doc3.getObjectId("_id");

        Document doc4 = new Document("name","java").append("faculty","meena").append("credits",3);
        Document doc5 = new Document("name","Add english").append("faculty","sneha").append("credits",2);
        Document doc6 = new Document("name","DBMS").append("faculty","jishnu").append("credits",2);
        booksCollection2.insertOne(doc4);
        ObjectId ob4=doc4.getObjectId("_id");

        booksCollection2.insertOne(doc5);
        ObjectId ob5=doc5.getObjectId("_id");

        booksCollection2.insertOne(doc6);
        ObjectId ob6=doc6.getObjectId("_id");


        Document doc7 = new Document("type","embedded").append("student",doc).append("course",doc4);
        Document doc8 = new Document("type","embedded").append("student",doc2).append("course",doc3);
        Document doc9 = new Document("type","embedded").append("student",doc3).append("course",doc6);
        booksCollection3.insertOne(doc7);
        booksCollection3.insertOne(doc8);
        booksCollection3.insertOne(doc9);

        Document doc10 = new Document("type","reference").append("student",ob).append("course",ob4);
        Document doc11 = new Document("type","reference").append("student",ob2).append("course",ob5);
        Document doc12 = new Document("type","reference").append("student",ob3).append("course",ob6);
        booksCollection4.insertOne(doc10);
        booksCollection4.insertOne(doc11);
        booksCollection4.insertOne(doc12);


    }
    catch (Exception e){
        System.out.println("Invalid Account number.");
    }


}

    public void display() {
        Scanner scan = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/";
        try {

            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("Student");
            MongoCollection<Document> booksCollection = database.getCollection("Student");
            MongoCollection<Document> booksCollection2 = database.getCollection("Course");
            MongoCollection<Document> booksCollection3 = database.getCollection("enroll");
            MongoCollection<Document> booksCollection4 = database.getCollection("enroll2");

            System.out.println("Embedded Type");
            for(Document doc:booksCollection3.find()){
                System.out.println(doc.toJson());
            }
            System.out.println("reference Type");
            for(Document doc:booksCollection4.find()){
                System.out.println(doc.toJson());
            }



        } catch (Exception e) {
            System.out.println("Invalid Account number.");
        }
    }
    public void updateembed()
    {Scanner scan = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/";
        try {

            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("Student");
            MongoCollection<Document> booksCollection = database.getCollection("Student");
            MongoCollection<Document> booksCollection2 = database.getCollection("Course");
            MongoCollection<Document> booksCollection3 = database.getCollection("enroll");
            MongoCollection<Document> booksCollection4 = database.getCollection("enroll2");

            System.out.println("update embedded doc");
            System.out.println("enter the name of the student");
            String name=scan.nextLine();

            System.out.println("enter the New name of the student");
            String name2=scan.nextLine();

            Bson filter = Filters.eq("student.name",name);
            Bson update= Updates.set("student.name",name2);
            booksCollection3.updateOne(filter, update);





        } catch (Exception e) {
            System.out.println("Invalid Account number.");
        }

    }
    public void updaterefer()
    {
        Scanner scan = new Scanner(System.in);
        String uri = "mongodb://localhost:27017/";
        try {
            System.out.println("Reference update");
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("Student");
            MongoCollection<Document> booksCollection = database.getCollection("Student");
            MongoCollection<Document> booksCollection2 = database.getCollection("Course");
            MongoCollection<Document> booksCollection3 = database.getCollection("enroll");
            MongoCollection<Document> booksCollection4 = database.getCollection("enroll2");


            System.out.println("enter the name of the student");
            String name=scan.nextLine();

            System.out.println("enter the New name of the student");
            String name2=scan.nextLine();

            System.out.println("reference Type");
            Bson projection = Projections.fields(Projections.exclude("type","_id","course"));
            for(Document doc:booksCollection4.find().projection(projection)){
                ObjectId ob=doc.getObjectId("student");

                Bson filter2 = Filters.eq("_id",ob);
                Bson projection2 = Projections.fields(Projections.exclude("_id","phone no","age"));
                Document doc2= booksCollection.find(filter2).projection(projection2).first();
//                System.out.println(doc2.toJson());
                String name3=doc2.getString("name");

                if(name.equals(name3)){
//                    System.out.println(name3);
                    Bson filter = Filters.eq("_id",ob);
                    Bson update= Updates.set("name",name2);
                    booksCollection.updateOne(filter, update);
                }


            }

//            Bson filter2 = Filters.eq(" ",recev);
//            Bson projection2 = Projections.fields(Projections.include("Balance"));
//            Document doc2= booksCollection.find(filter2).projection(projection2).first();
//            newbal=doc2.getDouble("Balance");





        } catch (Exception e) {
            System.out.println("Invalid Account number.");
        }
    }
}

