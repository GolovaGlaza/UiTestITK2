import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;
import utils.BaseDriver;


public class MainTest extends BaseDriver {

    private UsersPage usersPage;
    private InterviewPage interviewPage;
    private UserDropDownMenuPage userDropDownMenuPage;
    private AdditionalQuestionsPage additionalQuestionsPage;
    private ItkCoursePage itkCoursePage;


  @BeforeEach
  public void initPages(){
      usersPage = new UsersPage(driver);
      interviewPage = new InterviewPage(driver);
      userDropDownMenuPage = new UserDropDownMenuPage(driver);
      additionalQuestionsPage = new AdditionalQuestionsPage(driver);
      itkCoursePage = new ItkCoursePage(driver);
  }

    @Test
    public void searchUserTest() {
      String userName = "orlov_alexey";
      usersPage.open("/");
      usersPage.login();
      usersPage.searchUserByName(userName);
      usersPage.verifyUserExists(userName);
    }

    @Test
    public void dataChangeTest() {
      String date = "15/11/2024";
      interviewPage.open("/");
      interviewPage.login();
      interviewPage.interviewDateRedact(date);
      interviewPage.interviewCheckDate(date);
    }

    @Test
    public void logOutTest(){
      userDropDownMenuPage.open("/");
      userDropDownMenuPage.login();
      userDropDownMenuPage.logOut();
      userDropDownMenuPage.logOutCheck();
    }

    @ParameterizedTest
    @MethodSource("utils.DataProvider#csvQuestionData")
    @Execution(ExecutionMode.SAME_THREAD)
    public void createAdditionalQuestionTest(String question, String clarifyingQuestion){
      additionalQuestionsPage.open("/");
      additionalQuestionsPage.login();
      additionalQuestionsPage.createNewQuestion(question, clarifyingQuestion);
      additionalQuestionsPage.verifyNewQuestion(question);
    }

    @Test
    public void courseCardsTest(){
      int expectedCardCount = 13;
      itkCoursePage.setBaseUrl("ITK_TEST");
      itkCoursePage.open("/");
      itkCoursePage.login();
      itkCoursePage.checkCourseCards(expectedCardCount);
    }
}
