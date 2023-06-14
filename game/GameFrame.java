

// 게임의 전체적인 프레임입니다.


package game;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    public static final int BEGINNING_PANEL = -1; // 시작메뉴 패널
    public static final int SELECT_PANEL = 0; // 캐릭터 선택 패널
    public static final int RULE_PANEL = 1; // 룰 설명 패널
    public static final int CREDIT_PANEL = 2; // 제작자 패널

    private BeginningPanel beginningPanel;
    private SelectPanel selectPanel;
    private Map1Panel map1Panel;
    private CreditPanel creditPanel;
    private HelpPanel helpPanel;
    private JPanel currentPanel;

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
        creditPanel = new CreditPanel(this);

        setContentPane(beginningPanel);
        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }

    public void swapPanel(int selectedMenu) {
        // 이전 패널 제거
        if (currentPanel != null) {
            if (currentPanel instanceof CreditPanel) {
                ((CreditPanel) currentPanel).stopTimer();
            }
            remove(currentPanel);
        }
        switch (selectedMenu) {
            case BEGINNING_PANEL:
                currentPanel = beginningPanel;
                System.out.println("패널 변경 -> 시작 화면");
                break;
            case SELECT_PANEL:
                currentPanel = selectPanel;
                System.out.println("패널 변경 -> 캐릭터 선택");
                break;
            case RULE_PANEL:
                 helpPanel = new HelpPanel(this);
                 currentPanel = helpPanel;
                 setContentPane(helpPanel);
                 System.out.println("패널 변경 -> 규칙 설명");
                break;
            case CREDIT_PANEL:
                creditPanel = new CreditPanel(this);
                currentPanel = creditPanel;
                setContentPane(creditPanel);
                System.out.println("패널 변경 -> 제작자 패널");
                break;
        }
        setContentPane(currentPanel);
        validate();
        requestFocusInWindow();

    }

}