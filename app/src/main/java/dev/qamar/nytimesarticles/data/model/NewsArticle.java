package dev.qamar.nytimesarticles.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsArticle {

@SerializedName("url")
@Expose
private String url;
@SerializedName("column")
@Expose
private String section;
@SerializedName("byline")
@Expose
private String byline;
@SerializedName("type")
@Expose
private String type;
@SerializedName("title")
@Expose
private String title;
@SerializedName("abstract")
@Expose
private String _abstract;
@SerializedName("published_date")
@Expose
private String publishedDate;
@SerializedName("source")
@Expose
private String source;
@SerializedName("id")
@Expose
private Long id;
@SerializedName("views")
@Expose
private Long views;
@SerializedName("media")
@Expose
private List<Medium> media = null;

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getSection() {
return section;
}

public void setSection(String section) {
this.section = section;
}

public String getByline() {
return byline;
}

public void setByline(String byline) {
this.byline = byline;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getAbstract() {
return _abstract;
}

public void setAbstract(String _abstract) {
this._abstract = _abstract;
}

public String getPublishedDate() {
return publishedDate;
}

public void setPublishedDate(String publishedDate) {
this.publishedDate = publishedDate;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}


public Long getViews() {
return views;
}

public void setViews(Long views) {
this.views = views;
}

public List<Medium> getMedia() {
return media;
}

public void setMedia(List<Medium> media) {
this.media = media;
}

}