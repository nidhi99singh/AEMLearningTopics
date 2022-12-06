package com.adobe.aem.guides.wknd.core.services;

import com.google.gson.JsonObject;

public interface QueryBuilderSearchService {
    public JsonObject searchResult(String path);
}
