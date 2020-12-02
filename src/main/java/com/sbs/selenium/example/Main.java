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

import com.sbs.selenium.example.dto.DCinsideArticle;

public class Main {

	public static void main(String[] args) {

		printDCInsideTreeGalleryLatestArticles();

	}

	private static void printDCInsideTreeGalleryLatestArticles() {
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
		driver.get("https://gall.dcinside.com/board/lists/?id=tree");

		File file = new File("downloads");
		if (file.exists() == false) {
			file.mkdir();
		}

		Util.sleep(1000);

		List<WebElement> articlesElements = driver.findElements(By.cssSelector("tbody .ub-content.us-post"));
		List<DCinsideArticle> articles = new ArrayList<>();
		for (WebElement articleElement : articlesElements) {
			int id = Integer.parseInt(articleElement.findElement(By.cssSelector(".gall_num")).getText().trim());
			String title = articleElement.findElement(By.cssSelector(".gall_tit")).getText().trim();
			String writer = articleElement.findElement(By.cssSelector(".gall_writer")).getText().trim();
			String date = articleElement.findElement(By.cssSelector(".gall_date")).getText().trim();
			int count = Integer.parseInt(articleElement.findElement(By.cssSelector(".gall_count")).getText().trim());
			int recommend = Integer
					.parseInt(articleElement.findElement(By.cssSelector(".gall_recommend")).getText().trim());
			articles.add(new DCinsideArticle(id, title, writer, date, count, recommend));
		}

		System.out.println("==디시인사이드 식물갤 게시물 최신글 리스트==");
		System.out.println("번호 / 제목 / 작성자 / 작성일 / 조회수 / 추천수");

		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			String title = articles.get(i).getTitle();
			String writer = articles.get(i).getWriter();
			String date = articles.get(i).getDate();
			int hit = articles.get(i).getHit();
			int recommend = articles.get(i).getRecommend();
			System.out.printf("%d / %s / %s / %s / %d / %d\n", id, title, writer, date, hit, recommend);
		}

	}

}
