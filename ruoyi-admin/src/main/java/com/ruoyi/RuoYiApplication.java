package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

/**
 (node:13564) [DEP0060] DeprecationWarning: The `util._extend` API is deprecated. Please use Object.assign() instead.
 (Use `node --trace-deprecation ...` to show where the warning was created)
 Proxy error: Could not proxy request /captchaImage from localhost to http://localhost:8080/.
 See https://nodejs.org/api/errors.html#errors_common_system_errors for more information (ECONNREFUSED).

 Proxy error: Could not proxy request /captchaImage from localhost to http://localhost:8080/.
 See https://nodejs.org/api/errors.html#errors_common_system_errors for more information (ECONNREFUSED).

 Proxy error: Could not proxy request /captchaImage from localhost to http://localhost:8080/.
 See https://nodejs.org/api/errors.html#errors_common_system_errors for more information (ECONNREFUSED).

 Proxy error: Could not proxy request /captchaImage from 10.205.225.231 to http://localhost:8080/.
 See https://nodejs.org/api/errors.html#errors_common_system_errors for more information (ECONNREFUSED).

 Proxy error: Could not proxy request /captchaImage from localhost to http://localhost:8080/.
 See https://nodejs.org/api/errors.html#errors_common_system_errors for more information (ECONNREFUSED).
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
