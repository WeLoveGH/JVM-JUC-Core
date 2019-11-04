package com.logicbig.godtrue.multi.thread.ThreadSynchronization;


/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-10-25
 */
public class Livelock {

    public static void main(String[] args) {

        final LivelockWorker worker1 = new LivelockWorker("LivelockWorker 1 ", true);
        final LivelockWorker worker2 = new LivelockWorker("LivelockWorker 2", true);

        final LivelockCommonResource s = new LivelockCommonResource(worker1);

        new Thread(() -> {
            worker1.work(s, worker2);
        }).start();

        new Thread(() -> {
            worker2.work(s, worker1);
        }).start();
    }
}

class LivelockCommonResource {

    private LivelockWorker owner;

    public LivelockCommonResource(LivelockWorker d) {
        owner = d;
    }

    public LivelockWorker getOwner() {
        return owner;
    }

    public synchronized void setOwner(LivelockWorker d) {
        owner = d;
    }
}

class LivelockWorker {

    private String name;
    private boolean active;

    public LivelockWorker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(LivelockCommonResource commonResource, LivelockWorker otherWorker) {
        while (active) {
            // wait for the resource to become available.
            if (commonResource.getOwner() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    //ignore
                }
                continue;
            }

            // If other worker is also active let it do it's work first
            if (otherWorker.isActive()) {
                System.out.println(getName() +
                        " : handover the resource to the worker " +
                        otherWorker.getName());
                commonResource.setOwner(otherWorker);
                continue;
            }

            //now use the commonResource
            System.out.println(getName() + ": working on the common resource");
            active = false;
            commonResource.setOwner(otherWorker);
        }
    }
}