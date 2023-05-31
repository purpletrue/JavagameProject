// 기본 게임 프레임

package game;

import java.awt.*;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private BeginningPanel beginningPanel;
    private GamePanel gamePanel;

    public GameFrame(GamePanel gamePanel) {
        setTitle("BalloonDefense");
        setSize(1152, 864);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setResizable(false);

        this.gamePanel = gamePanel;
        beginningPanel = new BeginningPanel(this);
        setContentPane(beginningPanel);

        setVisible(true); // 프레임을 화면에 표시
    }

    public void switchToGamePanel() {
        setContentPane(gamePanel);
        gamePanel.setBackground(Color.WHITE);
        revalidate();
        gamePanel.requestFocusInWindow();
    }
}
