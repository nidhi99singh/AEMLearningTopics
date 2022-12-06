package com.adobe.aem.guides.wknd.core.models;

import java.util.List;
import java.util.Map;

public interface NavBar {

    List<String> getHeadings();

    List<Map<String, String>> getNavs();

    String getButtonText();

    String getButtonUrl();
}
