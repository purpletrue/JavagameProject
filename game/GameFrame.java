package game;

import java.awt.*;

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

    public GameFrame() {
        setTitle("코딩의 호흡");
        setSize(1152, 864);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension frameSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        setResizable(false);

        beginningPanel = new BeginningPanel(this);
        selectPanel = new SelectPanel(this);

        setContentPane(beginningPanel);

        setVisible(true);
    }
    public GameFrame(JPanel panel) {
        currentPanel = panel;

        initializeFrame();
        initializePanel();
    }

    private void initializeFrame() {
        setSize(Main2.WINDOW_WIDTH, Main2.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Game Frame");
    }

    private void initializePanel() {
        setContentPane(currentPanel);
        validate();
        currentPanel.requestFocusInWindow();
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
        validate(); // 새로운 컨텐트 패널을 적용하기 위해 validate() 호출
    }
}
