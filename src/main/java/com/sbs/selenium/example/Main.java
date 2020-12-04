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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sbs.selenium.example.dto.DCinsideArticle;
import com.sbs.selenium.example.dto.DaumNewsArticle;
import com.sbs.selenium.example.dto.NaverNewsArticle;

public class Main {

	public static void main(String[] args) {

		printDaumNews();

	}

	private static void printDaumNews() {
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
		driver.get("https://news.daum.net/society#1");

		Util.sleep(1000);

		List<WebElement> articlesElements = driver.findElements(By.cssSelector(".list_timenews li"));
		List<DaumNewsArticle> articles = new ArrayList<>();

		for (WebElement articleElement : articlesElements) {
			String code = articleElement.findElement(By.cssSelector(".tit_timenews a")).getAttribute("href");
			code = code.split("v/")[1];
			String date = articleElement.findElement(By.cssSelector(".txt_time")).getText().trim();
			String title = articleElement.findElement(By.cssSelector(".tit_timenews a")).getText().trim();

			articles.add(new DaumNewsArticle(code, date, title));

		}

		for (int i = 0; i < articles.size(); i++) {
			String code = articles.get(i).getCode();
			String date = articles.get(i).getDate();
			String title = articles.get(i).getTitle();
			System.out.println("--");
			System.out.printf("번호 : %s\n", code);
			System.out.printf("날짜 : %s\n", date);
			System.out.printf("제목 : %s\n", title);
			System.out.println("--");
		}

	}

	private static void printNaverNewsFirstPage() {
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
		driver.get("https://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=001");

		File file = new File("downloads/naverNewsFlash");
		if (file.exists() == false) {
			file.mkdirs();
		}

		Util.sleep(1000);

		System.out.println("== 네이버 뉴스 속보 첫 페이지 글 목록 ==");

		List<WebElement> articlesElements = driver.findElements(By.cssSelector("tbody .content .list_body ul li dl"));
		List<NaverNewsArticle> articles = new ArrayList<>();

		for (WebElement articleElement : articlesElements) {
			String code = articleElement.findElement(By.cssSelector("dt:not(.photo) a")).getAttribute("href").trim();
			code = code.split("aid=")[1];
			code = code.split("&")[0];
			String title = articleElement.findElement(By.cssSelector("dt:not(.photo)")).getText().trim();
			String body = articleElement.findElement(By.cssSelector("dd span.lede")).getText().trim();
			String writer = articleElement.findElement(By.cssSelector("dd span.writing")).getText().trim();
			String imgUrl = "";
			try {
				imgUrl = articleElement.findElement(By.cssSelector("dt.photo a img")).getAttribute("src").trim();
			} catch (NoSuchElementException e) {

			}

			articles.add(new NaverNewsArticle(code, title, body, writer, imgUrl));

			BufferedImage saveImage = null;

			try {
				saveImage = ImageIO.read(new URL(imgUrl));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (saveImage != null) {
				try {
					String fileName = code;
					ImageIO.write(saveImage, "jpg", new File("downloads/naverNewsFlash/" + fileName + ".jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

		for (int i = 0; i < articles.size(); i++) {
			String code = articles.get(i).getCode();
			String title = articles.get(i).getTitle();
			String body = articles.get(i).getBody();
			String writer = articles.get(i).getWriter();
			String imgUrl = articles.get(i).getImgUrl();
			System.out.println("--");
			System.out.printf("코드 : %s\n", code);
			System.out.printf("제목 : %s\n", title);
			System.out.printf("내용 : %s\n", body);
			System.out.printf("신문사 : %s\n", writer);
			System.out.printf("썸네일URL : %s\n", imgUrl);
			System.out.println("--");
		}
	}

	private static void downloadDCInsideTreeGalleryFristArticleThumbnail() {
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

		File file = new File("downloads/DCInsideTreeGallery");
		if (file.exists() == false) {
			file.mkdirs();
		}

		Util.sleep(1000);

		List<WebElement> firstArticles = driver.findElements(By.cssSelector("tbody tr.ub-content.us-post"));
		WebElement firstArticle = firstArticles.get(0);
		firstArticle = firstArticle.findElement(By.cssSelector("td.gall_tit.ub-word a"));
		firstArticle.click();

		WebElement Thumbnail = null;

		Thumbnail = driver.findElement(By.cssSelector(".gallview_contents .writing_view_box div:nth-child(3) img"));

		String src = Thumbnail.getAttribute("src");

		BufferedImage saveImage = null;

		try {
			saveImage = ImageIO.read(new URL(src));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (saveImage != null) {
			try {
				String fileName = src;
				ImageIO.write(saveImage, "jpg", new File("downloads/DCInsideTreeGallery/" + fileName + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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

		System.out.println("==디시인사이드 식물갤 게시물 최신글 리스트==");
		System.out.println("번호 / 제목 / 작성자 / 작성일 / 조회수 / 추천수");

		List<WebElement> articlesElements = driver.findElements(By.cssSelector("tbody .ub-content.us-post"));
		List<DCinsideArticle> articles = new ArrayList<>();
		for (WebElement articleElement : articlesElements) {
			int id = Util.getAsInt(articleElement.findElement(By.cssSelector(".gall_num")).getText().trim());
			String title = articleElement.findElement(By.cssSelector(".gall_tit")).getText().trim();
			String writer = articleElement.findElement(By.cssSelector(".gall_writer")).getText().trim();
			String date = articleElement.findElement(By.cssSelector(".gall_date")).getText().trim();
			int count = Util.getAsInt(articleElement.findElement(By.cssSelector(".gall_count")).getText().trim());
			int recommend = Util
					.getAsInt(articleElement.findElement(By.cssSelector(".gall_recommend")).getText().trim());
			articles.add(new DCinsideArticle(id, title, writer, date, count, recommend));
		}

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
