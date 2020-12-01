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

		WebElement filter = driver.findElement(By.cssSelector(".link_keyword"));
		filter.click();
		filter = driver.findElement(By.cssSelector(".list_filter .filter_item:nth-child(1) a:nth-child(3)"));
		filter.click();
		filter = driver.findElement(By.cssSelector(".list_filter .filter_item:nth-child(2) a:nth-child(3)"));
		filter.click();
		filter = driver.findElement(By.cssSelector(".list_filter .filter_item:nth-child(3) a:nth-child(3)"));
		filter.click();
		filter = driver.findElement(By.cssSelector(".list_filter .filter_item:nth-child(4) a:nth-child(3)"));
		filter.click();
		filter = driver.findElement(By.cssSelector(".list_filter .filter_item:nth-child(5) a:nth-child(3)"));
		filter.click();
		filter = driver.findElement(By.cssSelector(".filter_area .list_age_wrap .list_age li:nth-child(2)"));
		filter.click();
		filter = driver.findElement(By.cssSelector("button.btn_set"));
		filter.click();
		List<WebElement> titleElements = driver.findElements(By.cssSelector("div#NM_RTK_VIEW_list_wrap div:nth-child(2) ul:first-of-type.list_realtime a.link_keyword span.keyword"));
		
		for (WebElement titleElement : titleElements) {
			
			String src = titleElement.getText();
			
			System.out.println(src);
			
			
			
		}

	}
}
