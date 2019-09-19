package Movie;

public class movie {
	private String mvID;  //아이디
	private String mvTheaterID; //상영관 아이디	
	private String mvMovieTitle; //영화제목
	private String mvDirector; //영화 감독
	private String mvGrade; //등급
	private String mvActor;	//영화감독
	private String mvInfo;//주요정보
	private String mvAccumulateNum;//누적예매수
	
	
	public String getMvAccumulateNum() {
		return mvAccumulateNum;
	}
	public String getMvTheaterID() {
		return mvTheaterID;
	}
	public void setMvTheaterID(String mvTheaterID) {
		this.mvTheaterID = mvTheaterID;
	}
	public void setMvAccumulateNum(String mvAccumulateNum) {
		this.mvAccumulateNum = mvAccumulateNum;
	}
	public String getMvID() {
		return mvID;
	}
	public void setMvID(String mvID) {
		this.mvID = mvID;
	}
	
	public String getMvMovieTitle() {
		return mvMovieTitle;
	}
	public void setMvMovieTitle(String mvMovieTitle) {
		this.mvMovieTitle = mvMovieTitle;
	}
	public String getMvDirector() {
		return mvDirector;
	}
	public void setMvDirector(String mvDirector) {
		this.mvDirector = mvDirector;
	}
	public String getMvActor() {
		return mvActor;
	}
	public void setMvActor(String mvActor) {
		this.mvActor = mvActor;
	}
	public String getMvGrade() {
		return mvGrade;
	}
	public void setMvGrade(String mvGrade) {
		this.mvGrade = mvGrade;
	}
	public String getMvInfo() {
		return mvInfo;
	}
	public void setMvInfo(String mvInfo) {
		this.mvInfo = mvInfo;
	}
}
