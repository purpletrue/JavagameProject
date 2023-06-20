

// psvm이 있는 메인 코드입니다.


package game;

import javax.swing.JFrame;

public class Main {
    private static GameFrame frame;
    private static Map1Panel map1Panel;
    private static Map1Panel map2Panel;
    private static Map1Panel map3Panel;

    public static void main(String[] args) {
        frame = new GameFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        BeginningPanel beginningPanel = new BeginningPanel(frame);
        frame.setContentPane(beginningPanel);

        frame.setVisible(true);
    }

    public static Map1Panel getMap1Panel() {
        return map1Panel;
    }
    //    public static Map2Panel getMap2Panel() {
//        return map2Panel;
//    }
//    public static Map3Panel getMap3Panel() {
//        return map3Panel;
//    }
    public static void setMap1Panel(Map1Panel map1Panel) {
        Main.map1Panel = map1Panel;
    }
}