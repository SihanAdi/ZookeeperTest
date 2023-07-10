package com.adsh.zookeepertest;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ZookeeperTestApplicationTests {

    @Autowired
    CuratorFramework curatorFramework;

    @Test
    void createNode() throws Exception {
        String path = curatorFramework.create().forPath("/curator-node", "somedata".getBytes());

//        String path = curatorFramework.create()
//                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
//                .forPath("/curator-node", "somedata".getBytes());

        System.out.println("Curator create node " + path + " successfully");

//        System.in.read();
    }

    @Test
    void getData() throws Exception {
        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
        System.out.println(new String(bytes));
    }

    @Test
    void setData() throws Exception {
        curatorFramework.setData().forPath("/curator-node", "changed".getBytes());
        getData();
    }

    @Test
    void createWithParent() throws Exception {
        String path = curatorFramework.create().creatingParentsIfNeeded().forPath("/node-parent/sub1");
        System.out.println("Curator create node " + path + " successfully");
    }

    @Test
    void delete() throws Exception {
        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath("/node-parent");
    }

    @Test
    void addNodeListener() throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/curator-node");
        nodeCache.getListenable().addListener(
                new NodeCacheListener() {
                    @Override
                    public void nodeChanged() throws Exception {
                        log.info("{} path nodeChanged", "/curator-node");
                        byte[] bytes = curatorFramework.getData().forPath("/curator-node");
                        log.info("data: {}", new String(bytes));
                    }
                }
        );

        nodeCache.start();
        System.in.read();
    }

}
