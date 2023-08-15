package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String serviceVersion;
    private final String title;

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    private final String mediaType;
    private final String url;

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getUrl() {
        return url;
    }

    public Post(@JsonProperty("copyright") String copyright,
                @JsonProperty("date") String date,
                @JsonProperty("explanation") String explanation,
                @JsonProperty("hdurl") String hdurl,
                @JsonProperty("media_type") String mediaType,
                @JsonProperty("service_version") String serviceVersion,
                @JsonProperty("title") String title,
                @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }
}
/*
{"copyright":"\nWioleta Gorecka;
"date":"2023-08-15",
"explanation":"
 "hdurl":"https://apod.nasa.gov/apod/image/2308/TripleIceland_Zarzycka_6501.jpg",
 "media_type":"image","service_version":"v1","title":"A Triply Glowing Night Sky over Iceland",
 "url":"https://apod.nasa.gov/apod/image/2308/TripleIceland_Zarzycka_1080.jpg"}

 */