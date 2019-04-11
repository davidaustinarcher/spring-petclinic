package org.springframework.samples.petclinic.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.samples.petclinic.PetClinicApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@WebAppConfiguration
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes={PetClinicApplication.class})
@ContextConfiguration(classes = ITConfig.class)
public class IntegrationTests {

    @Autowired
    private WebDriver webDriver;

    @LocalServerPort
    private int serverPort;
    
    
    @Test
    public void searchForOwner() throws Exception {
    		final String url = String.format("http://localhost:%d/owners/find", serverPort);
    		
    		System.out.println("searchForOwner: " + url);
        this.webDriver.get(url);
        //Thread.sleep(1 * 1000);
        
        WebElement lastName = this.webDriver.findElement(By.name("lastName"));
        Assert.assertNotNull(lastName);
        
        Actions actions = new Actions(this.webDriver);
        
        actions.moveToElement(lastName);
        actions.click();
        actions.sendKeys("Davis");
        actions.perform();

        lastName.submit();

        // Wait for results to load.
        //Thread.sleep(1 * 1000);

        List<WebElement> owners = this.webDriver.findElements(By.cssSelector("a[href*='owners']"));
        Assert.assertNotNull(owners);
        Assert.assertThat(owners.size(), is(equalTo(3)));		// 2 results + 1 find
    }
    
    
    @Test
    public void hitPages() throws Exception {
		final String url = String.format("http://localhost:%d", serverPort);
    	for ( int i = 0; i<5; i++ ) {
    		this.webDriver.get( url + "/owners/" + i );
    		this.webDriver.get( url + "/owners/" + i + "/pets/new");
    		this.webDriver.get( url + "/owners/" + i + "/edit" );    		
    		this.webDriver.get( url + "/owners/" + i + "/pets/" + i + "/edit");
    		this.webDriver.get( url + "/owners/" + i + "/pets/" + i + "/visits/new");
    	}
		this.webDriver.get( url + "/oups" );
		this.webDriver.get( url + "/vets.xml" );
		this.webDriver.get( url + "/vets.html" );
		this.webDriver.get( url + "/vets.json" );
		this.webDriver.get( url + "/owners" );
		this.webDriver.get( url + "/owners/find" );
		this.webDriver.get( url + "/owners/new" );
		
		this.webDriver.get( url + "/favicon.ico");
		this.webDriver.get( url + "/manage/metrics");
		this.webDriver.get( url + "/manage/metrics.json");
		this.webDriver.get( url + "/manage/beans");
		this.webDriver.get( url + "/manage/beans.json");
		this.webDriver.get( url + "/manage/loggers");
		this.webDriver.get( url + "/manage/loggers.json");
		this.webDriver.get( url + "/manage/heapdump");
		this.webDriver.get( url + "/manage/heapdump.json");
		this.webDriver.get( url + "/manage/trace");
		this.webDriver.get( url + "/manage/trace.json");
		this.webDriver.get( url + "/manage/info");
		this.webDriver.get( url + "/manage/info.json");
		this.webDriver.get( url + "/manage/health");
		this.webDriver.get( url + "/manage/health.json");
		this.webDriver.get( url + "/manage/dump");
		this.webDriver.get( url + "/manage/dump.json");
		this.webDriver.get( url + "/manage/configprops");
		this.webDriver.get( url + "/manage/configprops.json");
		this.webDriver.get( url + "/manage/auditevents");
		this.webDriver.get( url + "/manage/auditevents.json");
		this.webDriver.get( url + "/manage/autoconfig");
		this.webDriver.get( url + "/manage/autoconfig.json");
		this.webDriver.get( url + "/manage/mappings");
		this.webDriver.get( url + "/manage/mappings.json");
		this.webDriver.get( url + "/manage/env");
		this.webDriver.get( url + "/manage/env.json"); 		
    }
    
    


}
