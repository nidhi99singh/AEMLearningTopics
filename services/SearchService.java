package com.adobe.aem.guides.wknd.core.services;

import java.util.Map;

import com.google.gson.JsonObject;

public interface SearchService {

    public JsonObject searchResult(String searchText, int startResult, int resultPage);

    public JsonObject searchResultSQL2(String searchPath);
}
