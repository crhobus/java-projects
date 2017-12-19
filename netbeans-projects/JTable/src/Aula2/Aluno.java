package Aula2;


public class Aluno {

	private String nome;
	private float media;
	private boolean pagou;
	
	public Aluno(String nome, float media, boolean pagou) {
		super();
		this.nome = nome;
		this.media = media;
		this.pagou = pagou;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getMedia() {
		return media;
	}
	public void setMedia(float media) {
		this.media = media;
	}
	public boolean isPagou() {
		return pagou;
	}
	public void setPagou(boolean pagou) {
		this.pagou = pagou;
	}
	
	
	
}
