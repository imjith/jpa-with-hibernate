package in.sjstudio.hibernate.advanced.repository;

import static org.junit.Assert.assertEquals;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Passport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PassportRepositoryTest {


  @Autowired
  private PassportRepository passportRepo;

  @Test
  @Transactional
  public void testFindById() {
    Passport passport = passportRepo.findById(30001L);
    assertEquals("L8434899", passport.getnumber());
    assertEquals("Sreejith Sreekantan", passport.getStudent().getName());
  }
}
