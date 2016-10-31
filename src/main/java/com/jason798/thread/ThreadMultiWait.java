package com.jason798.thread;

import com.jason798.common.SystemUtil;
import com.jason798.dto.TestDto;
import com.jason798.queue.IQueue;
import com.jason798.queue.QueueManager;
import com.jason798.queue.impl.BlockingQueueEncap;
import java.util.LinkedList;
import java.util.List;


public class ThreadMultiWait {

    private boolean start;

    public static void main(String[] args) throws InterruptedException {
    }

    public static void simWaitQueueNotify() throws InterruptedException {
        QueueManager.addQueue("q1",new BlockingQueueEncap());
        IQueue q = QueueManager.getQueue("q1");
        WaitThread t = new WaitThread(q);
        t.start();
        SendThread st = new SendThread(q);
        st.start();

        for( int i=0; i<10000000; i++){
            System.out.println("sleep 5s");
            Thread.sleep(1000);
            if(i%5==0){//every 5 seconds enter this area
                if( i% 2==1 ) {
                    System.out.println("############stop consume#############");
                    synchronized (t){
                        t.wait();
                    }
                    SystemUtil.sleep(100*1000);
                }else{
                    System.out.println("############start consume###########");
                    synchronized (t){
                        t.notifyAll();
                    }
                }
            }
        }
    }

    public static void simWait() throws InterruptedException {
        QueueManager.addQueue("q1",new BlockingQueueEncap());
        IQueue q = QueueManager.getQueue("q1");
        WaitThread t = new WaitThread(q);
        t.start();
        SendThread st = new SendThread(q);
        st.start();
        for( int i=0; i<10000000; i++){
            System.out.println("sleep 5s");
            Thread.sleep(1000);
            if(i%5==0){//every 5 seconds enter this area
                if( i% 2==1 ) {
                    System.out.println("############stop consume#############");
                    synchronized (t){
                        t.wait();
                    }
                    Thread.sleep(100*1000);
                }else{
                    System.out.println("############start consume###########");
                    synchronized (t){
                        t.notifyAll();
                    }
                }
            }
        }
    }


    public static class WaitThread extends Thread{
        private IQueue queue;
        private List<Object> data = new LinkedList<>();
        public WaitThread(IQueue q){
            this.queue = q;
        }

        public void run(){
            System.out.println("wait start run");
            while (true) {
                try {
                    Object o = queue.receiveMessage();
                    if(o!=null){

                    }else{
                        System.out.println("notified no data recv,list "+data);
                    }
                    System.out.println("recv object "+o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    public static class SendThread extends Thread{
        private IQueue queue;
        private int sleepInterval = 1000;

        public SendThread(IQueue q){
            this.queue = q;
        }

        public SendThread(IQueue q,int sleepInterval){
            this.queue = q;
            this.sleepInterval = sleepInterval;
        }

        public void run(){
            System.out.println("send start run");
            int i=0;
            while (true) {
                try {
                    TestDto d = new TestDto(i);
                    queue.sendMessage(d);
                    System.out.println("send object "+d);
                    Thread.sleep(sleepInterval);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
