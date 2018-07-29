import com.yangyh.dao.*;
import com.yangyh.entity.*;
import com.yangyh.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")

public class adminTest {
    @Autowired
    private AdminService adminService;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private SlideshowService slideshowService;
    @Autowired
    private SlideshowDao slideshowDao;
    @Autowired
    private EssayDao essayDao;
    @Autowired
    private GurnService gurnService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JournalService journalService;
    @Autowired
    private JournalDao journalDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private MusicDao musicDao;
    @Test
    public void test1(){
        Admin find = adminService.queryAdmin("徐鑫辉", "123456");
        System.out.println(find);
    }
    @Test
    public void test22(){
        List<Menu> servicefindlist = menuDao.servicefindlist();
        for (Menu menu : servicefindlist) {
            System.out.println(menu);
        }
    }

    @Test
    public void trwewqe(){
        Essay essay = new Essay();
        essay.setId("3");
        essay.setTitle("标题");
        essay.setIssuetime(new Date());
        essay.setTime(new Date());
        essay.setGurn(new Guru("1"));
        essay.setContent("12341654563132123");
        essayDao.serviceadd(essay);
    }
    @Test
    public void tesrt2(){
        List<Slideshow> servicequerylist = slideshowService.servicequerylist();
        System.out.println(servicequerylist);
    }
    @Test
    public void ttwqe2(){
        List<User> list = userService.ServicePaging(0, 5);
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void tewrw(){
        User user = new User();
        user.setId("1");
        user.setName("1523");
        user.setPassword("123456");
        user.setEmail("123");
        user.setSex("name");
        user.setWeix("123");
        user.setPhone("123456");
        user.setState("gdsag");
        user.setFhname("12321132546");
        user.setQq("123");
        user.setGuru(new Guru("1"));
        user.setHeadurl("1231");

        userService.Serviceupdate(user);
    }

    @Test
    public void tewrtwe(){


        List<Usersite> servicefindman = userService.Servicefindman();
        System.out.println(servicefindman);

        List<Usersite> servicefindwoman = userService.Servicefindwoman();
        System.out.println(servicefindwoman);
    }

    @Test
    public void tewrw123(){
        List<Journal> journals = journalService.ServicePaging(1, 5);
        System.out.println(journals);
    }
    @Test
    public void tewr11(){
        Journal jj = new Journal();
        jj.setId("2");
        jj.setAdmin("张良");
        jj.setTime(new Date());
        jj.setAction("查询");
        jj.setResult("成功");
        journalService.Serviceadd(jj);
    }

    @Test
    public void fewrf(){
        List<Usersite> servicefindman = userService.Servicefindman();
        System.out.println(servicefindman);
    }
    @Test
    public void tewr(){
        Journal js = new Journal();
              js.setId("9724201807172207");
        journalService.Servicedelete(js);
    }
    @Test
    public void tewt1(){
        Journal js = new Journal();
        js.setId("2");
        journalDao.delete(js);
    }
    @Test
    public void tewr23(){
        List<Journal> journals = journalService.ServicePaging(1, 5);
        for (Journal journal : journals) {
            System.out.println(journal);
        }
        double i = 424/3;
        double b =299;
        double c=i+b;
        System.out.println(c);
        List<Journal> findlist = journalDao.findlist();
        for (Journal journal : findlist) {
            System.out.println(journal);
        }
    }
    @Test
    public void twetrwe(){
        List<Album> list = albumDao.findlist();
        System.out.println(list);
        List<Album> paging = albumDao.Paging(1, 5);
        System.out.println(paging);
    }
    @Test
    public void trwetwerew(){
        List<Album> servicefindlist = albumService.Servicefindlist();
        System.out.println("这是查所有"+servicefindlist);

        List<Album> albums = albumService.ServicePaging(1, 5);
        System.out.println("这是分页"+albums);
    }

    @Test
    public void fdsf(){
        Album al = new Album();
        al.setId("3");
        al.setAlbumname("天龙八部之宿敌");
        al.setTeller("许嵩");
        al.setPending("2");
        al.setAlbumurl("/upload/rose.jpg");
        al.setAlbuintro("古风");
        al.setIssuealbutime(new Date());
        al.setStars("/upload/1x.jpg");
        albumDao.add(al);
    }
    @Test
    public void fgdsfsd(){
        Music music = new Music();
     //   music.setId("5");
       // music.setAid("2");
        musicDao.add(music);
    }
    @Test
    public void fwerw(){
        Album album = albumDao.find("2");
        System.out.println(album);
    }


}
