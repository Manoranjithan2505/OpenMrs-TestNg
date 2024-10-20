package Com.TESTNG;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BASECLASS {
public static WebDriver driver;
	
	public static ExtentReports extentReports;
	
	public static File file;
	
	//Browser Launch
	protected static WebDriver browserLaunch(String browserName) {
		try {
			if(browserName.equalsIgnoreCase("chrome")) {
				driver=new ChromeDriver();
			} else if(browserName.equalsIgnoreCase("edge")) {
				driver=new EdgeDriver();
			} else if(browserName.equalsIgnoreCase("firefox")) {
				driver=new FirefoxDriver();
			} else {
				System.out.println("Invalid Browser Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	//Launch URL
	protected static void getUrl(String url) {
		driver.get(url);
	}
	
	//Navigate Methods
	protected static void navigateMethods(String navigateMethod, String navigateUrl) {
		try {
			if(navigateMethod.equals("back")) {
				driver.navigate().back();
			} else if(navigateMethod.equalsIgnoreCase("forward")) {
				driver.navigate().forward();
			} else if(navigateMethod.equalsIgnoreCase("refresh")) {
				driver.navigate().refresh();
			} else if(navigateMethod.equalsIgnoreCase("to")) {
				driver.navigate().to(navigateUrl);
			} else {
				System.out.println("Method invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Implicit Wait
	protected static void implicitWait(WebDriver driver, int time) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Explicit Wait
	protected static void explicitWait(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Title
	protected static void getTitle(WebDriver driver) {
		try {
			String title=driver.getTitle();
			System.out.println("Title is: "+title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Get Text
	protected static void getText(WebDriver driver, WebElement element) {
		try {
			String text= element.getText();
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Click
	public static void clickElement(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Send Keys
	public static void inputData(WebDriver driver, WebElement element, String value) {
		try {
			element.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Take Screenshot
	protected static void takesScreenshot(WebDriver driver, String filename, String format) {
		TakesScreenshot shot=(TakesScreenshot) driver;
		File source=shot.getScreenshotAs(OutputType.FILE);
		File destination=new File("C:\\Users\\splpt 1201\\eclipse-workspace\\New folder" + "\\" + filename + ".png");
		try {
			FileHandler.copy(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Current URL
	protected static void currentUrl(WebDriver driver, String currentUrl) {
		try {
			String currentPageUrl=driver.getCurrentUrl();
			System.out.println("Current page URL is: "+currentPageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Drop down
	protected static void dropdown(WebDriver driver, WebElement element, String options, String value) {
		Select select=new Select(element);
		try {
			if(options.equalsIgnoreCase("value")) {
				select.selectByValue(value);
			}else if(options.equalsIgnoreCase("index")) {
				select.selectByIndex(Integer.parseInt(value));
			}else if(options.equalsIgnoreCase("text")) {
				select.selectByVisibleText(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Actions and Move to element
	protected static void actions(WebDriver driver, WebElement element) {
		try {
			Actions mouse=new Actions(driver);
			mouse.dragAndDrop(element, element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected static void moveToElement(WebDriver driver, WebElement element) {
		try {
			Actions mouse=new Actions(driver);
			mouse.moveToElement(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Get Attribute Value
	protected static void getAttribute(WebDriver driver, WebElement element, String attribute) {
		try {
			String value= element.getAttribute(attribute);
			System.out.println("Attribute values are: "+value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Alert
	public void simpleAlert(WebDriver driver, WebElement element) {
		try {
			explicitWait(driver, element);
			Alert simpleAlert = driver.switchTo().alert();
			simpleAlert.accept();
			driver.switchTo().defaultContent();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

//	public void confirmAlert(WebDriver driver, WebElement element, String condition) {
//		try {
//			explicitWait(driver, element);
//			Alert confirmAlert = driver.switchTo().alert();
//			if (condition.equalsIgnoreCase("accept")) {
//				confirmAlert.accept();
//				driver.switchTo().defaultContent();
//			} else if (condition.equalsIgnoreCase("dismiss")) {
//				confirmAlert.dismiss();
//				driver.switchTo().defaultContent();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void confirmAlert(String condition) {
		try {
//			explicitWait(driver, element);
			Alert confirmAlert = driver.switchTo().alert();
			if (condition.equalsIgnoreCase("accept")) {
				confirmAlert.accept();
				driver.switchTo().defaultContent();
			} else if (condition.equalsIgnoreCase("dismiss")) {
				confirmAlert.dismiss();
				driver.switchTo().defaultContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//is Enabled
	protected static void isEnabled(WebDriver driver, WebElement element) {
		try {
			if(element.isEnabled()) {
				System.out.println("Element enabled");
			} else {
				System.out.println("Element disabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//is Displayed
	protected static void isDisplayed(WebDriver driver, WebElement element) {
		try {
			if(element.isDisplayed()) {
				System.out.println("Element displayed");
			} else {
				System.out.println("Element not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//is Selected
	protected static void isSelected(WebDriver driver, WebElement element) {
		try {
			if(element.isSelected()) {
				System.out.println("Element selected");
			} else {
				System.out.println("Element not selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Java Script Executor
	public static void mouseHoverToElement(WebDriver driver, WebElement element) {
		try {
			explicitWait(driver, element);
			
			Actions ac = new Actions(driver);
			ac.moveToElement(element).build().perform();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void scrollUsingElement(WebElement element, String option) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0], scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void scrollUsingCoOrdinates(String width, String height) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(" + width + "," + height + ")");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void scrollToBottomOfthePage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void scrollToTopOfThePage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void dragAndDrop(WebElement sourceElement, WebElement placedElement) {
		try {
			Actions ac = new Actions(driver);
			ac.dragAndDrop(sourceElement, placedElement);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	//Frames
	protected static void switchToFrameUsingId(String option, int index) {

		try {
			if (option.equals("id")) {
				driver.switchTo().frame(index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected static void switchToFrameUsingName(String option, String Framename) {

		try {
			if (option.equals("name")) {
				driver.switchTo().frame(Framename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected static void switchToFrameUsingElement(String option, WebElement element) {

		explicitWait(driver, element);
		try {
			if (option.equals("element")) {
				driver.switchTo().frame(element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void switchToDefault() {

		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void extentReportStart(String location)	{
		extentReports = new ExtentReports();
		file = new File(location);
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));		
	}
	
	public static void extentReportTearDown(String location) throws IOException {
		extentReports.flush();
		file = new File(location);
		Desktop.getDesktop().browse((file).toURI());;
	}
	
	public String takeScreenshot() throws IOException {
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File srcfile=screenshot.getScreenshotAs(OutputType.FILE);
		File destfile=new File("Screenshot\\.png" + "_" + timestamp + ".png");
		FileUtils.copyFile(srcfile, destfile);
		return destfile.getAbsolutePath();
	}
	
	public static void validation(WebDriver act, String exp) {
		Assert.assertEquals(act.getTitle(), exp);
	}
	
	public static void validationone(WebElement act, String exp) {
		Assert.assertEquals(act.getText(), exp);
	}
	
	public static void stringSelection(String path) {
		StringSelection ss=new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}
	
	public static void robotKeys(String options) {
		try {
			Robot r=new Robot();
			if(options.equalsIgnoreCase("delay")) {
				r.delay(2000);
			}else if(options.equalsIgnoreCase("ControlVP")) {
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);
			}else if(options.equalsIgnoreCase("ControlVR")) {
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_V);
			}else if(options.equalsIgnoreCase("EnterP")) {
				r.keyPress(KeyEvent.VK_ENTER);
			}else if(options.equalsIgnoreCase("EnterR")) {
				r.keyRelease(KeyEvent.VK_ENTER);
			}
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	//Close
	protected static void browserClose() {
		driver.close();
	}
	
	//Quit
	protected static void browserTerminate() {
		driver.quit();
	}
	


}

