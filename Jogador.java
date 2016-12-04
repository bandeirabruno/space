package br.com.space.control;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Jogador {
	
	public int x,y;
	public int dx,dy;
	public Image imagem;
	private int alt,larg;
	private boolean isVisivel;
	private List<Missel> misseis;

	public Jogador(){
		ImageIcon nave = new ImageIcon("res\\player_red.png");
		imagem = nave.getImage();
		
		alt=imagem.getHeight(null);
		larg=imagem.getWidth(null);
		misseis = new ArrayList<Missel>();
		this.x=300;
		this.y=500;
	}
	public void mover(){
		x+=dx;
		y+=dy;
		
		if(this.x < 1){
			x=1;
		}
		if(this.y > 630){
			y=630;
		}
		if(this.x > 650){
			x=650;
		}
		if(this.y < 300){
			y=300;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImagem() {
		return imagem;
	}
	
	public List<Missel> getMisseis() {
		return misseis;
	}

	public void atira(){
		this.misseis.add(new Missel(x+larg/2, y-20));
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public void keyPressed(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_SPACE){
			atira();
		}
		if(codigo == KeyEvent.VK_UP){
			dy = -1;
		}
		if(codigo == KeyEvent.VK_DOWN){
			dy = 1;
		}
		if(codigo == KeyEvent.VK_LEFT){
			dx = -1;
		}
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 1;
		}
		if(codigo == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = 0;
		}
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,larg,alt);
	}
}
