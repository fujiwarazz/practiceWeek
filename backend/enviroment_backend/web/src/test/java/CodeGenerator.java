import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 逆向工程
 */
public class CodeGenerator {

    public static void main(String[] args) {
        List<String>names = new ArrayList<>();
        names.add("grid_city");
        names.add("grid_member");
        names.add("grid_province");

        for (String name : names) {
            generate(name);

        }
    }

    private static void generate(String name ) {

        FastAutoGenerator.create("jdbc:mysql://43.139.34.35:3306/environment?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8",
                        "root", "20020217")

                .globalConfig(builder -> {
                    builder.author("peelsannaw") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\JavaProjects\\sjz\\backend\\enviroment_backend\\web\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.peels") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\JavaProjects\\sjz\\backend\\enviroment_backend\\web\\src\\main\\java\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
//                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
//                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude(name) // 设置需要生成的表名
                            .addTablePrefix("k_", "sys_"); // 设置过滤表前缀
                })
                .execute();
    }
}
