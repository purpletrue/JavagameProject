package game;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    private JPanel currentPanel;
    public static final int BEGINNING_PANEL = -1; // 시작메뉴 패널
    public static final int SELECT_PANEL = 0; // 캐릭터 선택 패널
    public static final int RULE_PANEL = 1; // 룰 설명 패널
    public static final int PROD_PANEL = 2; // 제작자 패널

    private BeginningPanel beginningPanel;
    private SelectPanel selectPanel;
    private Map1Panel map1Panel;

    private int characterType;

    public GameFrame() {
        setTitle("코딩의 호흡");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int frameWidth = 1152;
        int frameHeight = 864;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        setBounds(x, y, frameWidth, frameHeight);
        setResizable(false);

        beginningPanel = new BeginningPanel(this);
        selectPanel = new SelectPanel(this);

        setContentPane(beginningPanel);
        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }

    public void swapPanel(int selectedMenu) {
        switch (selectedMenu) {
            case BEGINNING_PANEL:
                setContentPane(beginningPanel);
                break;
            case SELECT_PANEL:
                setContentPane(selectPanel);
                break;
            case RULE_PANEL:
                // Add code to create and set the rulePanel
                // rulePanel = new RulePanel(this);
                // setContentPane(rulePanel);
                // System.out.println("패널 변경 -> 규칙 설명");
                break;
            case PROD_PANEL:
                // Add code to create and set the prodPanel
                // prodPanel = new ProdPanel(this);
                // setContentPane(prodPanel);
                // System.out.println("패널 변경 -> 제작자 패널");
                break;
        }
        validate();
        requestFocusInWindow();
    }

    public void startGame(int selectedCharacterType) {
        characterType = selectedCharacterType;
        map1Panel = new Map1Panel(this, characterType);
        setContentPane(map1Panel);
        validate();
        map1Panel.requestFocusInWindow();
        map1Panel.startGameThread();
    }
}