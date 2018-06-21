package wanli.pojo;


public class Label {

  private long id;
  private String labelname;
  private long parentid;
  private String url;
  private long viewperm;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLabelname() {
    return labelname;
  }

  public void setLabelname(String labelname) {
    this.labelname = labelname;
  }


  public long getParentid() {
    return parentid;
  }

  public void setParentid(long parentid) {
    this.parentid = parentid;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public long getViewperm() {
    return viewperm;
  }

  public void setViewperm(long viewperm) {
    this.viewperm = viewperm;
  }

}
