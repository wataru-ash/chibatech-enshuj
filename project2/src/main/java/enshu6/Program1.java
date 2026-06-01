import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program1 extends JPanel implements ActionListener {
    private double time = 0;
    private Timer timer;

    public Program1() {
        // 課題の指定通り、ウィンドウ（パネル）サイズを400x400に設定
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.BLACK);
        
        // アニメーション用のタイマー（約33ミリ秒ごとに描画更新、約30fps）
        timer = new Timer(33, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // 円の描画を滑らかにするためのアンチエイリアス処理
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIASING_ON);

        int centerX = 200;
        int centerY = 200;
        int numArms = 6;      // 渦巻きの腕の数（動画に合わせて6本）
        int numDots = 20;     // 1つの腕あたりの円の数
        int dotDiameter = 10; // 課題の指定通り、円の直径は10

        for (int arm = 0; arm < numArms; arm++) {
            // 各腕の基準となる角度 (6等分)
            double baseAngle = arm * (2 * Math.PI / numArms);

            for (int i = 1; i <= numDots; i++) {
                // 中心からの距離
                double radius = i * 8.0;
                
                // 角度の計算
                // timeを加算することで時計回りに回転させ、
                // (i * 0.1)を引くことで外側ほど遅れて渦を巻く形状を表現
                double angle = baseAngle + time - (i * 0.1);

                // 円の左上座標を計算 (中心座標からオフセットを引いて円の中心を合わせる)
                int x = (int) (centerX + radius * Math.cos(angle)) - dotDiameter / 2;
                int y = (int) (centerY + radius * Math.sin(angle)) - dotDiameter / 2;

                // 色の計算: 中心は緑 (0, 255, 0)、外側へ行くほど白 (255, 255, 255) に近づく
                float ratio = (float) (i - 1) / (numDots - 1);
                int colorVal = (int) (255 * ratio); // 赤と青の成分を増やす
                g2d.setColor(new Color(colorVal, 255, colorVal));

                // 指定された直径で円を描画
                g2d.fillOval(x, y, dotDiameter, dotDiameter);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 回転速度を調整（値を大きくすると速くなる）
        time += 0.05;
        repaint(); // 画面を再描画
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Program1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Program1 panel = new Program1();
        frame.add(panel);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}