package com.adsh.zookeepertest;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestReadWriteLock {

    @Autowired
    CuratorFramework curatorFramework;

    @Test
    void getReadLock() throws Exception {
        //ReadWriteLock
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock1");
        //Get read lock Object
        InterProcessLock interProcessLock = interProcessReadWriteLock.readLock();
        System.out.println("Waiting for getting Read Lock");
        //Attempt to obtain a read lock, if no lock is obtained, it will be blocked
        interProcessLock.acquire();

        for (int i = 0; i < 100; i++){
            Thread.sleep(3000);
            System.out.println(i);
        }
        //Releasing lock
        interProcessLock.release();
        System.out.println("Releasing the read lock");
    }

    @Test
    void getReadLock1() throws Exception {
        //ReadWriteLock
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock1");
        //Get read lock Object
        InterProcessLock interProcessLock = interProcessReadWriteLock.readLock();
        System.out.println("Waiting for getting Read Lock");
        //Attempt to obtain a read lock, if no lock is obtained, it will be blocked
        interProcessLock.acquire();

        for (int i = 0; i < 100; i++){
            Thread.sleep(3000);
            System.out.println(i);
        }
        //Releasing lock
        interProcessLock.release();
        System.out.println("Releasing the read lock");
    }

    @Test
    void getWriteLock() throws Exception {
        //ReadWriteLock
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock1");
        //Get write lock Object
        InterProcessLock interProcessLock = interProcessReadWriteLock.writeLock();
        System.out.println("Waiting for getting write Lock");
        //Attempt to obtain a Write lock, if no lock is obtained, it will be blocked
        interProcessLock.acquire();

        for (int i = 0; i < 100; i++){
            Thread.sleep(3000);
            System.out.println(i);
        }
        //Releasing lock
        interProcessLock.release();
        System.out.println("Releasing the write lock");
    }
}
