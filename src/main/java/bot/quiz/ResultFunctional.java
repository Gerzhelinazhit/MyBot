package bot.quiz;

import bot.dao.ResultDao;
import bot.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultFunctional {
  @Autowired
    private ResultDao resultDao;


  public void increaseResult(Long key){
      if ( resultDao.getByKey(Long.valueOf(key).intValue())==null) {
          ResultEntity result = new ResultEntity();
                  System.out.println(result.toString());
          result.setIdUser(Long.valueOf(key).intValue());
          result.setRightAnswers(1);
          result.setWrongAnswers(0);
          resultDao.persist(result);
      } else {
          ResultEntity result = resultDao.getByKey(Long.valueOf(key).intValue());
          System.out.println(result.toString());
          result.setRightAnswers(result.getRightAnswers() + 1);
          resultDao.update(result);
      }
  }

  public void decreaseResult(Long key){
      ResultEntity result = resultDao.getByKey(Long.valueOf(key).intValue());
      System.out.println(result.toString());
      result.setRightAnswers(result.getRightAnswers()-1);
      resultDao.persist(result);
  }

  public String getResult(){
      List<ResultEntity> resultList =resultDao.getAll();
      StringBuilder sb = new StringBuilder();
      sb.append("Результат: \n");
      for (ResultEntity result: resultList) {
          sb.append(result.getUserByIdUser().getFirstName()+" " + result.getUserByIdUser().getLastName()+" - ");
          sb.append(result.getRightAnswers()+"\n");
      }
      return sb.toString();
  }
}
