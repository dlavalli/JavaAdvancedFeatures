package com.lavalliere.daniel.projects;


import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.security.*;
import java.util.*;

// REMOVED in Java 23
//import static java.util.FormatProcessor.FMT;

@IsDemoable
public class NewJava21Features implements Demoable {

    // NOTE: record was introduced in java 17
    public record Product(String id, String name, double price) {}

    public NewJava21Features showOldCollections() {
        // Old way
        var arrayList = new ArrayList<>(Arrays.asList("Banana", "Cherry", "Date"));
        arrayList.add(0, "Apple");
        arrayList.add(arrayList.size(), "Elderberry");
        System.out.println(arrayList);
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(arrayList.size()-1));


        var deque = new ArrayDeque<>(Arrays.asList("Banana", "Cherry", "Date"));
        deque.addFirst("Apple");
        deque.addLast("Elderberry");
        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

        var linkedHashSet = new LinkedHashSet<>(Arrays.asList("Banana", "Cherry", "Date"));
        linkedHashSet.add("Apple");
        System.out.println(linkedHashSet);
        System.out.println(linkedHashSet.iterator().next());
        System.out.println(linkedHashSet.toArray()[linkedHashSet.size() -1]);

        System.out.println();
        return this;
    }

    public NewJava21Features showSequencedCollections() {
        // Old way
        var arrayList = new ArrayList<>(Arrays.asList("Banana", "Cherry", "Date"));
        arrayList.addFirst("Apple");
        arrayList.addLast("Elderberry");
        System.out.println(arrayList);
        System.out.println(arrayList.getFirst());
        System.out.println(arrayList.getLast());


        var deque = new ArrayDeque<>(Arrays.asList("Banana", "Cherry", "Date"));
        deque.addFirst("Apple");
        deque.addLast("Elderberry");
        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

        var linkedHashSet = new LinkedHashSet<>(Arrays.asList("Banana", "Cherry", "Date"));
        linkedHashSet.addFirst("Apple");
        linkedHashSet.addLast("Elderberry");
        System.out.println(linkedHashSet);
        System.out.println(linkedHashSet.getFirst());
        System.out.println(linkedHashSet.getLast());

        System.out.println();
        return this;
    }

    public NewJava21Features testKeyEncapsulationMechanism() {  // ie: KEM
        try {
            // ---------------------------------------------------------------
            // Identical steps for old and new methods
            // ---------------------------------------------------------------
            // Identical steps for old and new methods
            final var keyPairGenerator = KeyPairGenerator.getInstance(
                // The algorithm used to generate the key pair
                // See https://docs.oracle.com/en/java/javase/17/docs/specs/security/standard-names.html#keypairgenerator-algorithms
                "X25519"
            );

            // NOTE : could use var BUT wanted to see the types returned
            final KeyPair keyPair = keyPairGenerator.generateKeyPair();
            final PublicKey publicKey = keyPair.getPublic();
            final PrivateKey privateKey = keyPair.getPrivate();

            // ---------------------------------------------------------------
            // Sender side of the exchange,
            // ---------------------------------------------------------------
            // see https://docs.oracle.com/en/java/javase/22/docs/specs/security/standard-names.html#kem-algorithms
            // The sender is an encapsulator. This can be used to get an encapsulated object
            // which contains a secret key and a key encapsulation message
            final KEM sendersKem = KEM.getInstance("DHKEM");
            final KEM.Encapsulator sender = sendersKem.newEncapsulator(publicKey);
            final KEM.Encapsulated encapsulated = sender.encapsulate();

            // encapsulated can then be used to get to the secret key
            final var secretKey = encapsulated.key();

            // ---------------------------------------------------------------
            // Receiver side of the exchange
            // ---------------------------------------------------------------
            final KEM receiversKem = KEM.getInstance("DHKEM");
            final KEM.Decapsulator receiver = receiversKem.newDecapsulator(privateKey);
            final SecretKey receivedSecretKey = receiver.decapsulate(encapsulated.encapsulation());

            if (Arrays.equals(secretKey.getEncoded(), receivedSecretKey.getEncoded())) {
                System.out.println("Keys match");
            } else {
                System.out.println("Keys do not match");
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException |DecapsulateException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println();

        return this;
    }



    @Override()
    public void demo() {
        this.showOldCollections()
            .showSequencedCollections()
            .testKeyEncapsulationMechanism()
            // .testForeignFunctionAndMemory()
        // .showStringTemplates()
        ;
    }
}
