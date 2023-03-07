import org.junit.jupiter.api.Test;

public class StringTest {

    //对文件名后缀进行截取
    //String中lastIndexOf()和subString(beginIndex，endIndex)【包含开始不包含结束】的用法
    @Test
    public void testStringFunction(){
        String file = "abcd.jpg";
        System.out.println(file.lastIndexOf('.'));
        System.out.println(file.substring(1, 3));
        System.out.println(file.substring(0));
        System.out.println(file.substring(0, 1));

        System.out.println(file.substring(file.lastIndexOf('.')));
        System.out.println(file.substring(file.lastIndexOf('.')+1));
    }
}
