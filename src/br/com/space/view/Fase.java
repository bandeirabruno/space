package br.com.space.view;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.com.space.control.Inimigo;
import br.com.space.control.Missel;
import br.com.space.control.Jogador;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Jogador nave;
	private Timer timer;
	public ImageIcon imagem;
	private Inimigo inimigo;

	private boolean jogando;
	private boolean win = false;

	private int vidas = 3;
	private int pts = 0;

	private List<Inimigo> inimigos;

	private int[][] coordenadas = {
			{ 110, 0 }, { 440, -1000 }, { 610, -5452 },// { 510, -2510 }, { 10, -1154 },
			/*
			 * { 444, -3300 },{ 500, -5410 },{ 170, -22144 },{ 11, -4560 },{ 0,
			 * -30486 }, { 331, 0 },{ 400, -3084651 },{ 400, -31541 },{ 180,
			 * -6468 },{ 20, -24682 }, { 222, -4880 },{ 177, -25145 },{ 300,
			 * -2641 },{ 91, -99659 },{ 80, -4512 },//{linha,coluna}
			 */ };

	public Fase() {

		setDoubleBuffered(true);
		setFocusable(true);
		addKeyListener(new TecladoAdapter());

		ImageIcon imagem = new ImageIcon("res\\fundo.jpg");
		fundo = imagem.getImage();
		nave = new Jogador();

		jogando = true;
		inicializaInimgos();

		timer = new Timer(5, this);
	}

	public void inicializaInimgos() {
		inimigos = new ArrayList<Inimigo>();
		/*
		 * while(inimigos.size()<20){ int l,c; l=0; c=(int)(Math.random()*700);
		 * inimigos.add(new Inimigo(c,l)); }
		 */
		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
		}
	}

	public boolean colisao() {
		Rectangle minhaNave = nave.getBounds();
		Rectangle novoInimigo;
		Rectangle novoMissel;

		//inimigo te acerta
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo tempInimigo = inimigos.get(i);
			novoInimigo = tempInimigo.getBounds();

			if (minhaNave.intersects(novoInimigo)) {
				nave.setVisivel(false);
				tempInimigo.setVisivel(false);
				vidas--;
				if (vidas == 0)
					jogando = false;
			}
		}

		//acerta inimigo
		List<Missel> misseis = nave.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {
			Missel tempMisseis = misseis.get(i);
			novoMissel = tempMisseis.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo tempInimigo = inimigos.get(j);
				novoInimigo = tempInimigo.getBounds();

				if (novoMissel.intersects(novoInimigo)) {
					tempInimigo.setVisivel(false);
					tempMisseis.setVisivel(false);
					pts++;
					return true;
				}
			}
		}
		return false;
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		// denenho da tela de fundo
		graficos.drawImage(fundo, 0, 0, null);

		if (jogando) {

			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

			// carregamento de misseis
			List<Missel> misseis = nave.getMisseis();
			for (int i = 0; i < misseis.size(); i++) {
				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			//inimigos
			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo enemy = (Inimigo) inimigos.get(i);
				graficos.drawImage(enemy.getImagem(), enemy.getX(), enemy.getY(), this);
			}
		} else {
			//game over
			ImageIcon gameover = new ImageIcon("res\\game_over.jpg");
			graficos.drawImage(gameover.getImage(), 30, 300, null);
		}

		graficos.setColor(Color.BLUE);
		graficos.drawString("VIDAS: " + vidas, 5, 15);
		graficos.drawString("PONTOS: " + pts, 5, 30);

		timer.start();

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (inimigos.size() == 0) {
			win = true;
		}
		// locomocao dos tiros
		List<Missel> misseis = nave.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {
			Missel m = (Missel) misseis.get(i);

			if (m.isVisivel()) {
				m.atirar();
			} else {
				misseis.remove(i);
			}
		}

		for (int j = 0; j < inimigos.size(); j++) {
			Inimigo enemy = (Inimigo) inimigos.get(j);

			if (enemy.isVisivel()) {
				enemy.mover();
			} else {
				inimigos.remove(j);
			}
		}
		nave.mover();
		colisao();
		repaint();
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent arg0) {
			nave.keyPressed(arg0);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			nave.keyReleased(arg0);
		}

	}

}
