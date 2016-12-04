package br.com.space.control;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {
	private Image imagem;
	private Image des;
	private int x, y;
	private int larg, alt;

	private boolean isVisivel;

	private static int aux = 0;
	private static final int limite_altura_tela = 0;
	private static int vel_enemy = 1;

	public Inimigo(int x, int y) {

		this.x = x;
		this.y = y;
		ImageIcon img_enemy,dest;

		dest=new ImageIcon("res\\dano1.png");
		des= dest.getImage();
		aux=(int) (Math.random()*10);
		if (aux % 2 == 0) {
			img_enemy = new ImageIcon("res\\enemyBlue1.png");
		} else {
			img_enemy = new ImageIcon("res\\enemyBlue2.png");
		}
		imagem = img_enemy.getImage();

		larg = imagem.getWidth(null);
		alt = imagem.getHeight(null);

		isVisivel = true;
	}

	public void mover() {

		if (this.y > 900) {
			this.y = limite_altura_tela-10;
		} else {
			this.y += vel_enemy;
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}
	
	public Image getDes(){
		return des;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, larg, alt);
	}
}
