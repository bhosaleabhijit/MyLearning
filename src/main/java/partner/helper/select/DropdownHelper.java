package partner.helper.select;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import partner.helper.logger.LoggerHelper;

public class DropdownHelper {

	private WebDriver driver;
	Logger log = LoggerHelper.getLogger(DropdownHelper.class);

	DropdownHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
		log.info("Selected value: " + value + " from dropdown " + element.toString());
	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Selected index: " + index + " from dropdown " + element.toString());
	}

	public void selectUsingVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		log.info("Selected visible text: " + value + " from dropdown " + element.toString());
	}

	public void deSelectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		select.deselectByValue(value);
		log.info("DeSelected value: " + value + " from dropdown " + element.toString());
	}

	public void deSelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);
		log.info("DeSelected index: " + index + " from dropdown " + element.toString());
	}

	public void deSelectUsingVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.deselectByVisibleText(value);
		log.info("DeSelected visible text: " + value + " from dropdown " + element.toString());
	}

	public void deselectAllOptions(WebElement element) {
		Select select = new Select(element);
		select.deselectAll();
		log.info("All options deselected from dropdown: " + element.toString());
	}

	public List<String> getAllDropdownData(WebElement element) {
		Select select = new Select(element);
		List<WebElement> optionslist = select.getOptions();
		List<String> valueList = new ArrayList<String>();

		for (WebElement el : optionslist) {
			valueList.add(el.toString());
		}

		return valueList;
	}

}
