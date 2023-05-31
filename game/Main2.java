package game;

// 메인화면. 버튼 누를 시 동작하는 화면
public class Main2 {
    static int WINDOW_WIDTH = 1152;
    static int WINDOW_HEIGHT = 864;
    private static GamePanel gamePanel;

    public static void main(String[] args) {
        gamePanel = new GamePanel();
        new GameFrame(gamePanel);
    }

    public static GamePanel getGamePanel() {
        return gamePanel;
    }
}