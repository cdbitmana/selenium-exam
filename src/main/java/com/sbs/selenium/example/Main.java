package com.sbs.selenium.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	public static void main(String[] args) {

		Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");

		// WebDriver 경로 설정
		System.setProperty("webdriver.chrome.driver", path.toString());

		// WebDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함

		// WebDriver 객체 생성
		ChromeDriver driver = new ChromeDriver(options);

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(0));
		driver.get("https://www.naver.com/");

		File downloadsFolder = new File("downloads");

		if (downloadsFolder.exists() == false) {
			downloadsFolder.mkdir();
		}

		Util.sleep(1000);

		
		WebElement news = driver.findElement(By.cssSelector("#NM_NEWSSTAND_view_buttons a:first-child"));
		news.click();
		List<WebElement> titleElements = driver.findElements(By.cssSelector(".list_news li a"));
		for (WebElement titleElement : titleElements) {
			String src = titleElement.getText();
			
			System.out.println(src);
		}

	}
}
