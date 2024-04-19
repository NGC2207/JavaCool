import Frontend.InterfaceManager;

public class Main {
    static {
        // 在静态初始化块中加载库文件
        System.load("/usr/lib/jvm/java-11-openjdk-amd64/lib/libawt_xawt.so");
    }
    public static void main(String[] args) {
        InterfaceManager interfaceManager = new InterfaceManager();
        interfaceManager.display();
        interfaceManager.setupComponents();
    }
}
