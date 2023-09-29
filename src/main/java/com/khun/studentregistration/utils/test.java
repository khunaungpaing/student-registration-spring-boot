package com.khun.studentregistration.utils;

import java.io.File;
import java.io.IOException;

public class test {
    // Define a lock object to synchronize file copying
    private static final Object fileCopyLock = new Object();

    public static void main(String[] args) {
        // Example usage
        Thread thread1 = new Thread(() -> {
            copyFile("source.txt", "destination.txt");
        });

        Thread thread2 = new Thread(() -> {
            copyFile("another_source.txt", "another_destination.txt");
        });

        thread1.start();
        thread2.start();
    }

    public static void copyFile(String sourceFilePath, String destinationFilePath) {
        synchronized (fileCopyLock) {
            File sourceFile = new File(sourceFilePath);
            File destinationFile = new File(destinationFilePath);

            try {
                // Perform the file copy operation here
                // You can use FileInputStream and FileOutputStream, or any other method
                // for copying files.
                // Example:
                // FileUtils.copyFile(sourceFile, destinationFile);

                System.out.println("Copying file: " + sourceFilePath + " to " + destinationFilePath);
                // Simulate the copy operation with sleep
                Thread.sleep(2000);

                System.out.println("Copy completed: " + sourceFilePath + " to " + destinationFilePath);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
