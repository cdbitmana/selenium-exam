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
		driver.get("https://unsplash.com/t/nature");

		File downloadsFolder = new File("downloads");

		if (downloadsFolder.exists() == false) {
			downloadsFolder.mkdir();
		}

		Util.sleep(1000);

		List<WebElement> imgElements = driver.findElements(
				By.cssSelector("[data-test=\"masonry-grid-count-three\"] img[data-test=\"photo-grid-multi-col-img\"]"));

		for (WebElement imgElement : imgElements) {
			String src = imgElement.getAttribute("src");

			BufferedImage saveImage = null;

			try {
				saveImage = ImageIO.read(new URL(src));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (saveImage != null) {
				try {

					String fileName = src.split("/")[3];
					fileName = fileName.split("\\?")[0];
					ImageIO.write(saveImage, "jpg", new File("downloads/" + fileName + ".jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			System.out.println(src);
		}

	}
}
