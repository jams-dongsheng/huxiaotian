

import com.yingxiaotian.mapper.YxtAdminMapper;
import com.huxiaotian.pojo.YxtAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class Test01 {
    @Autowired
    private YxtAdminMapper adminMapper;
    @Test
    public void test01(){
        YxtAdmin wwb123 = adminMapper.selectByPrimaryKey("wwb123");
        System.out.println(wwb123);
    }
}
