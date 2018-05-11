package bot.victorina;

import bot.dao.ClsAnswerDao;
import bot.dao.ClsQuestDao;
import bot.entity.ClsQuestEntity;
import bot.entity.ClsAnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionGeneration {
    @Autowired
    private ClsQuestDao questDao;
    @Autowired
    private ClsAnswerDao answerDao;
    private String Question;
    private String Answer;
    public String Comment;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getComment() {
        return Comment;
    }

    public QuestionGeneration(List<ClsQuestEntity> questList, List<ClsAnswerEntity> answerList){

        int random=0+(int)(Math.random()*3);
        Question=questList.get(random).getQuestText();
        Answer=answerList.get(random).getAnswerText();
        Comment=answerList.get(random).getAnswerComment();
    }
}
