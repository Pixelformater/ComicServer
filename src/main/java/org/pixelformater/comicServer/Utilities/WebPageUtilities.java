package org.pixelformater.comicServer.Utilities;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.pixelformater.comicServer.Engines.DownloadEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebPageUtilities {

    private WebClient webClient;
    private static Logger logger = LoggerFactory.getLogger(DownloadEngine.class);

    public WebPageUtilities() {

        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setTimeout(2000);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

    }

    public HtmlPage getWebPage(String url){
        HtmlPage page=null;
        try{
            page = webClient.getPage(url);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return page;
    }

    public String getWebPageAsXml(String url){
        HtmlPage page=null;
        try{
            page = webClient.getPage(url);

        }catch (Exception e){
            logger.error(e.toString());
        }

        return page.asXml();
    }


}
