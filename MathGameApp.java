import java.util.Random;
import java.util.Scanner;

// ゲーム要素を持つクラスのためのインターフェース
interface Game {
    void startGame();
    void askQuestion();
    void checkAnswer(int userAnswer);
    void levelUp();
}

// 基本プレイヤークラス
class Player {
    protected String name;
    protected int level;
    protected int score;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.score = 0;
    }

    public void displayStatus() {
        System.out.println(name + "のレベル: " + level + ", スコア: " + score);
    }
}

// そうた君クラス（プレイヤークラスを継承）
class Sota extends Player implements Game {
    private Random random = new Random();
    private int num1, num2;
    private Scanner scanner = new Scanner(System.in);

    public Sota(String name) {
        super(name);
    }

    // ゲーム開始
    @Override
    public void startGame() {
        System.out.println("ゲームを開始します！がんばってね、" + name + "君！");
        for (int i = 0; i < 5; i++) {
            askQuestion();
        }
        System.out.println("ゲーム終了！最終スコアは: " + score);
    }

    // 掛け算問題を出題
    @Override
    public void askQuestion() {
        num1 = random.nextInt(level * 10) + 1;
        num2 = random.nextInt(level * 10) + 1;
        System.out.println(num1 + " x " + num2 + " はいくつですか？");
        int userAnswer = scanner.nextInt();
        checkAnswer(userAnswer);
    }

    // 回答をチェック
    @Override
    public void checkAnswer(int userAnswer) {
        if (userAnswer == num1 * num2) {
            score += 10;
            System.out.println("正解！スコア: " + score);
            if (score % 50 == 0) {
                levelUp();
            }
        } else {
            System.out.println("残念、間違いです。正解は " + (num1 * num2) + " です。");
        }
    }

    // レベルアップ
    @Override
    public void levelUp() {
        level++;
        System.out.println("レベルアップ！新しいレベル: " + level);
    }
}

// メインクラス
public class MathGameApp {
    public static void main(String[] args) {
        Sota sota = new Sota("そうた");
        sota.startGame();
    }
}

