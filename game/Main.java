package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    private BufferedImage backgroundImage;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        JPanel btnPanel = new JPanel(null); // 레이아웃을 null로 설정

        JButton btn1 = new JButton("게임 시작");
        btn1.setFont(new Font("Gothic", Font.BOLD, 30));
        JButton btn2 = new JButton("도움말");
        btn2.setFont(new Font("Gothic", Font.BOLD, 30));
        JButton btn3 = new JButton("제작자");
        btn3.setFont(new Font("Gothic", Font.BOLD, 30));

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/res/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel.setLayout(null); // 레이아웃을 null로 설정
        panel.add(btnPanel);

        // btnPanel의 크기를 설정
        btnPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // 버튼의 위치와 크기를 직접 설정
        btn1.setBounds(450, 300, 250, 60);
        btn2.setBounds(450, 400, 250, 60);
        btn3.setBounds(450, 500, 250, 60);

        btnPanel.add(btn1);
        btnPanel.add(btn2);
        btnPanel.add(btn3);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼1 눌렀을 때 실행할 코드
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼2 눌렀을 때 실행할 코드
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼3 눌렀을 때 실행할 코드
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("코딩의 호흡");
        frame.setVisible(true);
        frame.setSize(1152, 864);

        frame.add(panel);
    }
}
