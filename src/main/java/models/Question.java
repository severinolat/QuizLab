package models;

public class Question {

    private String questin;
    private String answer;

    public Question(String questin, String answer) {
        this.questin = questin;
        this.answer = answer;
    }

    public String getQuestin() {
        return questin;
    }

    public void setQuestin(String questin) {
        this.questin = questin;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean checkAnswer(String answer){
        return this.answer.equals(answer);
    }
}
