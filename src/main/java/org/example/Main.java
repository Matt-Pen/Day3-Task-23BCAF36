package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Student st= new Student();
        Scanner scan= new Scanner(System.in);

        while(true){
            System.out.println("Enter your choice:");
            System.out.println("1. Display all embedded and reference documents");
            System.out.println("2. update student name in embedded document");
            System.out.println("3. update student name in reference document");
            System.out.println("4. find a student record in embedded document");
            int ch;
            ch=scan.nextInt();
            switch(ch){
                case 1:
                    st.display();
                    break;
                case 2:
                    st.updateembed();
                    break;
                case 3:
                    st.updaterefer();
                    break;
                case 4:
                    st.findstud();
                    break;


            }
        }


        }

}
