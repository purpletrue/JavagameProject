

// 게임의 전체적인 프레임입니다.


package game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    public static final int BEGINNING_PANEL = -1; // 시작메뉴 패널
    public static final int SELECT_PANEL = 0; // 캐릭터 선택 패널
    public static final int RULE_PANEL = 1; // 룰 설명 패널
    public static final int CREDIT_PANEL = 2; // 제작자 패널
    public static final int MAP1_Panel = 3;

    private BeginningPanel beginningPanel;
    private SelectPanel selectPanel;
    private Map1Panel map1Panel;
    private CreditPanel creditPanel;
    private HelpPanel helpPanel;
    private JPanel currentPanel;

    int characterType;

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

    public void swapPanel(int selectedMenu) throws IOException {
        // 이전 패널 제거
        if (currentPanel != null) {
            if (currentPanel instanceof Map1Panel) {
                ((Map1Panel) currentPanel).stopGameThread();
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
                System.out.println("패널 변경 -> 규칙 설명");
                break;
            case CREDIT_PANEL:
                creditPanel = new CreditPanel(this);
                currentPanel = creditPanel;
                System.out.println("패널 변경 -> 제작자 패널");
                break;
            case MAP1_Panel:
                map1Panel = new Map1Panel(this, characterType);
                currentPanel = map1Panel;
                System.out.println("패널 변경 -> Map1");
                map1Panel.startGameThread(); // 게임 스레드 시작
                break;
//            case MAP2_Panel:
//                map2Panel = new Map1Panel(this, characterType);
//                currentPanel = map1Panel;
//                System.out.println("패널 변경 -> Map2");
//                map2Panel.startGameThread(); // 게임 스레드 시작
//                break;
        }
        // 새로운 패널 추가
        setContentPane(currentPanel);

        // 화면 갱신
        validate();
        repaint();

        requestFocusInWindow();

    }

}