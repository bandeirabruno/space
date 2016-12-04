package br.com.space.control;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missel {
	private Image imagem;
	private int x, y;
	private int alt, larg;
	private boolean isVisivel;

	private static final int limita_altura_tela = 0;
	private static final int vel_missel = 2;

	public Missel(int x, int y) {

		this.x = x;
		this.y = y;

		ImageIcon img_missel = new ImageIcon("res\\laser01.png");
		imagem = img_missel.getImage();

		larg = imagem.getWidth(null);
		alt = imagem.getHeight(null);

		isVisivel = true;
	}

	public void atirar() {
		this.y -= vel_missel;

		if (this.y < limita_altura_tela) {
			isVisivel = false;
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
