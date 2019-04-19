/**
 * VM Args: -Xss128k
 */
public class JavaVMstackOOM {

    private void dontStop(){
        while(true){

        }
    }


    public void stackLeakByThread() {
        while(true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }


    public static void main(String[] args) {
        JavaVMstackOOM oom = new JavaVMstackOOM();
        oom.stackLeakByThread();
    }
}
