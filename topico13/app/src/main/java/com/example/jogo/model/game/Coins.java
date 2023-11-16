package com.example.jogo.model.game;

import android.graphics.Canvas;
import android.util.Log;

import com.example.jogo.view.GameFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Coins {
    boolean show = false;
    private Screen screen;
    private ArrayList<Coin> coins;
    private int totalCoinsCollected = 0;
    int color;

    public Coins(int numCoins, Screen screen, GameFragment gameFragment, int color) {
        this.screen = screen;
        this.coins = new ArrayList();
        this.color = color;
        generateRandomCoinsInOneThirdOfScreen(numCoins);
    }

    public void generateRandomCoinsInOneThirdOfScreen(int numCoins) {
        Random random = new Random();

        int oneThirdScreenWidth = screen.getWidth() / 3;
        int oneThirdScreenHeight = screen.getHeight() / 3;

        for (int i = 0; i < numCoins; i++) {
            float randomX = random.nextFloat() * oneThirdScreenWidth;
            float randomY = random.nextFloat() * oneThirdScreenHeight;

            randomX += oneThirdScreenWidth;

            coins.add(new Coin(randomX, randomY, color));
        }
    }
    public int amountCoins(){
        return coins.size();
    }
    public void draw(Canvas canvas) {
        if(show){
            for (Coin coin : coins) {
                coin.draw(canvas);
            }
        }
    }
    public void run(Ball ball) {

        Iterator<Coin> iterator = coins.iterator();
        while (iterator.hasNext()) {
            Coin coin = iterator.next();
            if (isCollision(ball, coin)) {
                totalCoinsCollected++;
                iterator.remove();
            }
        }
    }
    public int getTotalCoinsCollected(){
        return totalCoinsCollected;
    }
    // Método para verificar a colisão entre a bola e a moeda
    private boolean isCollision(Ball ball, Coin coin) {
        float ballX = ball.getPosition().x;
        float ballY = ball.getPosition().y;
        float coinX = coin.getPosition().x;
        float coinY = coin.getPosition().y;

        // Lógica de verificação de colisão
        // Por exemplo, se a distância entre a bola e a moeda for menor que o raio da bola + raio da moeda,
        // consideramos que houve uma colisão
        float distance = calculateDistance(ballX, ballY, coinX, coinY);
        int ballRadius = ball.getRadius(); // Substitua getRadius() pela obtenção do raio da bola
        int coinRadius = coin.getRadius(); // Substitua getRadius() pela obtenção do raio da moeda

        return distance < (ballRadius + coinRadius);
    }

    private float calculateDistance(float x1, float y1, float x2, float y2) {
        float deltaX = x2 - x1;
        float deltaY = y2 - y1;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public void changeShow(){
        show = !show;
    }
    public boolean isEmpty(){
        return coins.isEmpty();
    }

}
