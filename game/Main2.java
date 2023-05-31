package game;

public class Main2 {
    static int WINDOW_WIDTH = 1152;
    static int WINDOW_HEIGHT = 864;
    private static Map1Panel map1Panel;

    public static void main(String[] args) {
        Map1Panel parent = null; // 올바른 parent 객체로 초기화해야 합니다.
        int characterType = 0;
        map1Panel = new Map1Panel(parent, characterType);
        new GameFrame(map1Panel);
    }

    public static Map1Panel getMap1Panel() {
        return map1Panel;
    }
}
