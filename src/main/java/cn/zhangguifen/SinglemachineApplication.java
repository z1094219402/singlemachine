package cn.zhangguifen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"*"})
public class SinglemachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinglemachineApplication.class, args);
	}

}
